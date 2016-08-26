package com.teamone.beautality.models.response;

/**
 * Created by oshhepkov on 24.08.16.
 */
public class ErrorResponse {
    private String error,errCode, errMsg;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
