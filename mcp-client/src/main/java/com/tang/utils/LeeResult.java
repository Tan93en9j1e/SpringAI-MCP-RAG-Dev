package com.tang.utils;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang.utils
 * ClassName: LeeResult
 * Author: tmj
 * Date: 2026/6/25 21:22
 * version: 1.0
 * Description:
 * 200 表示成功
 * 500 表示错误，信息在msg中
 * 501 bean验证错误，不管多少个错误都以Map形式返回
 * 502 拦截器拦截到用户token出错
 * 555异常抛出信息
 * 556 用户qq检验异常
 * 557 校验用户是否在CAS登录，用户门票的检验
 */
public class LeeResult {

    //业务状态
    private Integer status;

    //业务消息
    private String msg;

    //业务数据
    private Object data;

    private String ok;//不使用

    public static LeeResult build(Integer status, String msg, Object data) {
        return new LeeResult(status, msg, data);
    }

    public static LeeResult build(Integer status, String msg, Object data, String ok) {
        return new LeeResult(status, msg, data, ok);
    }

    public static LeeResult ok(Object data) {
        return new LeeResult(data);
    }

    public static LeeResult ok() {
        return new LeeResult(null);
    }

    public static LeeResult errorMsg(String msg) {
        return new LeeResult(500, msg, null);
    }

    public static LeeResult errorUserTicket(String msg) {
        return new LeeResult(557, msg, null);
    }

    public static LeeResult errorMap(Object data){
        return new LeeResult(501,"error",data);
    }

    public static LeeResult errorTokenMsg(String msg){
        return new LeeResult(502, msg, null);
    }

    public static LeeResult errorException(String msg){
        return new LeeResult(555, msg, null);
    }

    public static LeeResult errorUserQQ(String msg){
        return new LeeResult(556, msg, null);
    }

    public LeeResult(){

    }

    public LeeResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public LeeResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }
    public LeeResult(Object data) {
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }

    public Boolean isOK(){
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getOk() {
        return ok;
    }
    public void setOk(String ok) {
        this.ok = ok;
    }
}
