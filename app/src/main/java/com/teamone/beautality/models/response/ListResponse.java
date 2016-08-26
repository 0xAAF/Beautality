package com.teamone.beautality.models.response;

/**
 * Created by oshhepkov on 26.08.16.
 */
public class ListResponse extends ErrorResponse{

    private String limit, result, skip;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }
}
