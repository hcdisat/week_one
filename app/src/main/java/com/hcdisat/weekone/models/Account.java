package com.hcdisat.weekone.models;

import androidx.annotation.NonNull;

public class Account {

    private String mEmail;
    private String mPassword;

    public Account(String _email, String _password) {
        this.mEmail = _email;
        this.mPassword = _password;
    }

    public Account() {
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String _email) {
        this.mEmail = _email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String _password) {
        this.mPassword = _password;
    }

    @NonNull
    @Override
    public String toString() {
        return "Account{" +
                "_email='" + mEmail + '\'' +
                ", _password='" + mPassword + '\'' +
                '}';
    }
}
