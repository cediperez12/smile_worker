package com.smile.worker.Models;

public class UserCredential {
    public String user_email, user_password, user_phone;

    public UserCredential(String user_email, String user_password, String user_phone) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_phone = user_phone;
    }

    public UserCredential() {
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
