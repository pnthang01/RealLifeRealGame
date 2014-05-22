
package com.team.vo;

/**
 * Base object return to client
 *
 * @author Trong 23-11-2013 - 22:20:44
 */
public class RestObject {

    public static final int ERROR = 1;
    public static final int OK = 0;
    private int errorCode;
    private String msg;
    private Object data;

    /**
     * @return the status
     */
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the main data object
     */
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static RestObject fromData(Object data, String messageIfError) {
        RestObject ret = new RestObject();
        if (data == null) {
            ret.errorCode = ERROR;
            ret.msg = messageIfError;
        } else {
            ret.errorCode = OK;
            ret.data = data;
            ret.msg = "Success";
        }
        return ret;
    }

    /**
     * return the object with default blank error message
     *
     * @param data
     * @return RestObject
     */
    public static RestObject fromData(Object data) {
        return fromData(data, "Success");
    }

    public static RestObject successBlank() {
        RestObject ret = new RestObject();

        ret.errorCode = OK;
        ret.msg = "Success";

        return ret;
    }

    /**
     *
     */
    public RestObject() {
    }

}
