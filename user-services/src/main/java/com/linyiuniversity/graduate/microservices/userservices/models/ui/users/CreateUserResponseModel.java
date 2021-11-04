package com.linyiuniversity.graduate.microservices.userservices.models.ui.users;

import java.util.Date;

public class CreateUserResponseModel {
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userNumber;
    private String userPolitc;
    private String userSex;
    private int userIsPhoneVerified;
    private int userIsEmailVerified;
    private int userIsEnabled;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserPolitc() {
        return userPolitc;
    }

    public void setUserPolitc(String userPolitc) {
        this.userPolitc = userPolitc;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserIsPhoneVerified() {
        return userIsPhoneVerified;
    }

    public void setUserIsPhoneVerified(int userIsPhoneVerified) {
        this.userIsPhoneVerified = userIsPhoneVerified;
    }

    public int getUserIsEmailVerified() {
        return userIsEmailVerified;
    }

    public void setUserIsEmailVerified(int userIsEmailVerified) {
        this.userIsEmailVerified = userIsEmailVerified;
    }

    public int getUserIsEnabled() {
        return userIsEnabled;
    }

    public void setUserIsEnabled(int userIsEnabled) {
        this.userIsEnabled = userIsEnabled;
    }
}
