package cn.cupbread.mims.Component;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : CupOfBread
 * @version : 0.1.0
 * @date : 2021/05/24
 * @description :
 */

@Accessors(chain = true)
@Data
public class RetResponse implements Serializable {
    private final static String SUCCESS = "success";

    public int status;

    private String msg;

    private Object data;

    public  RetResponse makeOKRsp(int status) {
        return new RetResponse().setStatus(status).setMsg(SUCCESS);
    }

    public RetResponse makeOKRsp(int status,Object data) {
        return new RetResponse().setStatus(status).setMsg(SUCCESS).setData(data);
    }

    public RetResponse makeOKRsp(int status,String msg) {
        return new RetResponse().setStatus(status).setMsg(SUCCESS).setMsg(msg);
    }

    public RetResponse makeOKRsp(int status,String msg,Object data) {
        return new RetResponse().setStatus(status).setMsg(msg).setData(data);
    }

    public RetResponse makeErrRsp(int status,String msg) {
        return new RetResponse().setStatus(status).setMsg(msg);
    }

    public RetResponse makeErrRsp(int status,String msg,Object data) {
        return new RetResponse().setStatus(status).setMsg(msg).setData(data);
    }

}
