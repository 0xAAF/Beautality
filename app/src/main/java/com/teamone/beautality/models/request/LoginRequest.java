package com.teamone.beautality.models.request;

/**
 * Created by oshhepkov on 24.08.16.
 */
public class LoginRequest extends BaseRequest{
    String email, password;

    public LoginRequest(String app, String cli, String email, String password) {
        super(app,cli);
        this.app = app;
        this.cli = cli;
        this.email = email;
        this.password = password;
    }
}
