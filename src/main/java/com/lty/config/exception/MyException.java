package com.lty.config.exception;

/**
 *      自定义异常:
 *      1.用途（场景）:
 *          1.如在程序中查询某某个用户不存在,按2（流程）操作
 *      2.流程：
 *          1.在程序中使用 thros new MyException(参数...);
 *          2.在controller中使用 throws Exception抛出异常
 *          3.抛出异常后 异常统一处理类进行处理
 */
public class MyException extends RuntimeException{

    private String code;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * 带异常消息的构造
     *
     * @param message
     *            信息描述
     */
    public MyException(String message)
    {
        super(message);
    }

    /**
     * 带异常消息何编号的构造
     *
     * @param message
     *            信息描述
     * @param code
     *            编号
     */
    public MyException(String message,String code)
    {
        super(message);
        this.data = null;
        this.code = code;
    }

    /**
     * 带异常消息何编号的构造
     * @param data
     *            数据
     * @param message
     *            信息描述
     * @param code
     *            编号
     */
    public MyException(String data,String message,String code)
    {
        super(message);
        this.code = code;
        this.data = data;
    }


}
