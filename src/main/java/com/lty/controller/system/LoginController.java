package com.lty.controller.system;

import com.lty.config.authentication.JWTToken;
import com.lty.config.authentication.JWTUtil;
import com.lty.config.exception.MyException;
import com.lty.config.properties.SystemProperties;
import com.lty.config.util.DateUtil;
import com.lty.config.util.IPUtil;
import com.lty.config.util.MD5Util;
import com.lty.config.util.MyUtil;
import com.lty.config.util.ResultUtil;
import com.lty.dao.system.LoginLogMapper;
import com.lty.entity.system.ActiveUser;
import com.lty.entity.system.LoginLog;
import com.lty.entity.system.Result;
import com.lty.entity.system.User;
import com.lty.entity.system.UserConfig;
import com.lty.service.system.service.LoginLogService;
import com.lty.service.system.service.UserManager;
import com.lty.service.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Validated
@RestController
@Slf4j
public class LoginController {


    public static final String ERROR_MESSAGE = "用户名或密码错误";
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private SystemProperties properties;

    @PostMapping("/login")
    public Result login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password, HttpServletRequest request) throws Exception {
        username = StringUtils.lowerCase(username);
        password = MD5Util.encrypt(username, password);


        User user = this.userManager.getUser(username);

        if (user == null) {
            throw new MyException(ERROR_MESSAGE);
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new MyException(ERROR_MESSAGE);
        }
        if (User.STATUS_LOCK.equals(user.getStatus())) {
            throw new MyException("账号已被锁定,请联系管理员！");
        }
        // 更新用户登录时间
        this.userService.updateLoginTime(username);
        // 保存登录记录
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        this.loginLogService.saveLoginLog(loginLog);
        String token = MyUtil.encryptToken(JWTUtil.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
        String userId = this.saveTokenToRedis(user, jwtToken, request);
        user.setId(userId);
        Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);
        return ResultUtil.requestSuccess(userInfo.toString(),"认证成功");
    }

    @GetMapping("index/{username}")
    public Result index(@NotBlank(message = "{required}") @PathVariable String username) {
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        Long totalVisitCount = loginLogMapper.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = loginLogMapper.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = loginLogMapper.findTodayIp();
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        List<Map<String, Object>> lastSevenVisitCount = loginLogMapper.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        User param = new User();
        param.setUsername(username);
        List<Map<String, Object>> lastSevenUserVisitCount = loginLogMapper.findLastSevenDaysVisitCount(param);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return ResultUtil.requestSuccess(data);
    }





    @PostMapping("regist")
    public Result regist(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String phoneNum,
            @NotBlank(message = "{required}") String verifyCode,
            @NotBlank(message = "{required}") String password) throws Exception {
        return userService.regist(username, password,phoneNum,verifyCode);
    }

    private String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
        // TODO: 2020/6/19  创建在线用户表
        return activeUser.getId();
    }

    /**
     * 生成前端需要的用户信息，包括：
     * 1. token
     * 2. Vue Router
     * 3. 用户角色
     * 4. 用户权限
     * 5. 前端系统个性化配置信息
     *
     * @param token token
     * @param user  用户信息
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(JWTToken token, User user) {
        String username = user.getUsername();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("expireTime", token.getExpireAt());

        Set<String> roles = this.userManager.getUserRoles(username);
        userInfo.put("roles", roles);

        Set<String> permissions = this.userManager.getUserPermissions(username);
        userInfo.put("permissions", permissions);

        UserConfig userConfig = this.userManager.getUserConfig(String.valueOf(user.getUserId()));
        userInfo.put("config", userConfig);

        user.setPassword("it's a secret");
        userInfo.put("user", user);
        return userInfo;
    }
 

}
