package com.teamone.beautality.models.response;

/**
 * Created by oshhepkov on 24.08.16.
 */
public class LoginResponse extends ErrorResponse {
    private UserResponse result;

    public UserResponse getResult() {
        return result;
    }

    public void setResult(UserResponse result) {
        this.result = result;
    }
}
