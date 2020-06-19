package com.lty.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lty.entity.system.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog(LoginLog loginLog);
}
