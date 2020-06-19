package com.lty.config.util;

import com.lty.entity.system.Result;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class ResultUtil {

    private static String REQUEST_FAILED = "Request Failed";  //请求失败
    private static String REQUEST_SUCCESS = "Request Success";  //请求失败


    /**
     * 请求成功
     * @param data
     * @return
     */
    public static Result
    requestSuccess(Object data) {
        Result result = new Result();
        result.setCode("00");
        result.setMsg(REQUEST_SUCCESS);
        if(data==null||data.equals("")){
            result.setData("");
        }else{
            result.setData(data);
        }
        return  result;
    }

    /**
     * 请求成功
     * @param data
     * @return
     */
    public static Result requestSuccess(Object data,String msg) throws Exception{
        Result result = new Result();
        result.setCode("00");
        return checkSuccess(data, msg, result);
    }

    /**
     * 请求成功
     * @param data
     * @return
     */
    public static Result requestSuccess(Object data,String msg,String code) throws Exception{
        Result result = new Result();
        if(code==null||"".equals(code)){
            result.setCode("00");
        }else{
            result.setCode(code);
        }
        return checkSuccess(data, msg, result);
    }

    private static Result checkSuccess(Object data, String msg, Result result) throws UnsupportedEncodingException {
        if(msg==null||"".equals(msg)){
            result.setMsg(REQUEST_SUCCESS);
        }else{
            result.setMsg(msg);
        }
        if(data==null||data.equals("")){
            result.setData("");
        }
        return  result;
    }

    /**
     * 请求成功
     * @param
     * @return
     */
    public static Result success(Object o){
        Result result = new Result();
        result.setCode("00");
        result.setMsg(REQUEST_SUCCESS);
        result.setData(o);
        return  result;
    }

    /**
     * 请求失败
     * @return
     */
    public static  Result requestFailed(String msg){
        Result result = new Result();
        result.setCode("-1");
        result.setData(null);
        if(msg==null||"".equals(msg)){
            result.setMsg(REQUEST_FAILED);
        }else{
            result.setMsg(msg);
        }
        return  result;
    }


    /**
     * 请求失败
     * @return
     */
    public static  Result requestFailed(String msg,String code){
        Result result = new Result();
        result.setData(null);
        return checkNull(msg, code, result);
    }

    /**
     * 请求失败
     * @return
     */
    public static  Result requestFailed(Object data,String msg,String code){
        Result result = new Result();
        if(data==null||"".equals(data)){
            result.setData(null);
        }else{
            result.setData(data);
        }
        return checkNull(msg, code, result);
    }

    private static Result checkNull(String msg, String code, Result result) {
        if(msg==null||"".equals(msg)){
            result.setCode("-1");
        }else{
            result.setCode(code);
        }
        if(msg==null||"".equals(msg)){
            result.setMsg(REQUEST_FAILED);
        }else{
            result.setMsg(msg);
        }
        return  result;
    }


}
