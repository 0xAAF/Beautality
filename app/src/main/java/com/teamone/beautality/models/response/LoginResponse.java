package com.teamone.beautality.models.response;

/**
 * Created by oshhepkov on 24.08.16.
 */
public class LoginResponse {
    private String error,errCode, errMsg;
    private UserResponse result;

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

    public UserResponse getResult() {
        return result;
    }

    public void setResult(UserResponse result) {
        this.result = result;
    }
}
