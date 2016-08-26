package com.teamone.beautality.models.request;

/**
 * Created by oshhepkov on 25.08.16.
 */
public class ListRequest extends BaseRequest {

    private String coll, sess;
    public ListRequest(String app, String cli, String coll, String sess) {
        super(app, cli);
        this.coll = coll;
        this.sess = sess;
    }
}
