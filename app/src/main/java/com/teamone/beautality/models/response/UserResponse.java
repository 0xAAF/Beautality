package com.teamone.beautality.models.response;

import java.util.List;

/**
 * Created by oshhepkov on 24.08.16.
 */
public class UserResponse {
    private String _id, createdAt,updatedAt,email,password,username;
    private String emailVerified;
    private List<String> readACL,removeACL,updateACL;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public List<String> getReadACL() {
        return readACL;
    }

    public void setReadACL(List<String> readACL) {
        this.readACL = readACL;
    }

    public List<String> getRemoveACL() {
        return removeACL;
    }

    public void setRemoveACL(List<String> removeACL) {
        this.removeACL = removeACL;
    }

    public List<String> getUpdateACL() {
        return updateACL;
    }

    public void setUpdateACL(List<String> updateACL) {
        this.updateACL = updateACL;
    }
}