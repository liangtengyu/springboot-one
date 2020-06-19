package com.lty.config.exception;

import com.lty.entity.system.Result;
import com.lty.config.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/5/14.
 * 统一处理异常
 *      在controller层 throws Exception
 */
@ControllerAdvice
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception{
        if(e instanceof MyException){
            MyException myException = (MyException)e;
            logger.info(myException.getMessage());
            if(myException.getData()==null||"".equals(myException.getData())){
                myException.setData(myException.getMessage());
            }
            return ResultUtil.requestFailed(myException.getData(),myException.getMessage(),myException.getCode());
        }
        logger.error(e.getMessage()+"(异常)",e);  //这里必须是logger.error(str1,str2),需要在
        e.printStackTrace();
        return ResultUtil.requestFailed(e.getMessage());
    }


}
