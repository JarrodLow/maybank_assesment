package com.assesment.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BaseException extends RuntimeException{
    protected String msg;

    protected String exId;

    protected Object[] params;

    protected List<BaseException> baseExceptionList;

    protected Throwable wrpEx;

    public BaseException() {
        super();
        gnrExUid();
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
        gnrExUid();
    }

    public BaseException(String msg, Object[] params) {
        super(msg);
        this.params = params;
        gnrExUid();
    }

    public BaseException(Throwable ex) {
        super(ex);
        this.wrpEx = ex;
        gnrExUid();
    }

    public BaseException(String msg, Throwable ex) {
        this(msg);
        this.wrpEx = ex;
        gnrExUid();
    }

    public BaseException(String msg, Object[] params, Throwable ex) {
        this(msg, params);
        this.params = params;
        this.wrpEx = ex;
        gnrExUid();
    }

    public void addException(BaseException exception) {
        if (null == baseExceptionList) {
            baseExceptionList = new ArrayList<>();
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public List<BaseException> getBaseExceptionList() {
        return baseExceptionList;
    }

    public void setBaseExceptionList(List<BaseException> baseExceptionList) {
        this.baseExceptionList = baseExceptionList;
    }

    public Throwable getWrpEx() {
        return wrpEx;
    }

    public void setWrpEx(Throwable wrpEx) {
        this.wrpEx = wrpEx;
    }

    @Override
    public synchronized Throwable getCause() {
        return this.wrpEx;
    }

    public String toString() {
        StringBuilder exBuilder = new StringBuilder();

        exBuilder.append("BaseException { ").append("Message=").append(msg).append(" Exception ID=").append(exId)
                .append(" Parameters=").append(Arrays.toString(params)).append(" Nested Exceptions=").append(baseExceptionList)
                .append(" Wrapped Exception=").append(wrpEx).append(" }");

        return exBuilder.toString();
    }

    private void gnrExUid() {
        UUID uid = UUID.randomUUID();

        int hashUid = uid.toString().hashCode();
        this.exId = String.valueOf(hashUid).replace("-", "");
    }
}
