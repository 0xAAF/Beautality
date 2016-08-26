package com.teamone.beautality.models.request;

/**
 * Created by oshhepkov on 25.08.16.
 */
public class BaseRequest {
    String app,cli;

    public BaseRequest(String app, String cli) {
        this.app = app;
        this.cli = cli;
    }
}
