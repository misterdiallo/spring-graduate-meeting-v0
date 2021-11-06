package com.linyiuniversity.graduate.microservices.userservices.shared.users;

import java.io.Serializable;
import java.util.Date;

public class UserWithRoleDTO implements Serializable {
    /*
     *
     */
    private static final long serialVersionUID = -49395859493945954L;

    // only Here
    private String userId;

    private String userName;

    private String userPhone;

    private String userEmail;

    private String userNumber;

    // only Here
    private String userWeiXinOpenId;

    // only Here
    private String userPolitc;

    private String userSex;

    private String userPassword;

    // only Here
    private String userEncryptedPassword;

    private Date userLastUpdatedPassword;

    private int userIsPhoneVerified;

    private int userIsEmailVerified;

    private Long userRESTRICTION_ID;

    private int userIsEnabled;

    private Date userLastUpdateAt;

    private Date userCreatedAt;

    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public String getUserWeiXinOpenId() {
        return userWeiXinOpenId;
    }

    public void setUserWeiXinOpenId(String userWeiXinOpenId) {
        this.userWeiXinOpenId = userWeiXinOpenId;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEncryptedPassword() {
        return userEncryptedPassword;
    }

    public void setUserEncryptedPassword(String userEncryptedPassword) {
        this.userEncryptedPassword = userEncryptedPassword;
    }

    public Date getUserLastUpdatedPassword() {
        return userLastUpdatedPassword;
    }

    public void setUserLastUpdatedPassword(Date userLastUpdatedPassword) {
        this.userLastUpdatedPassword = userLastUpdatedPassword;
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

    public Long getUserRESTRICTION_ID() {
        return userRESTRICTION_ID;
    }

    public void setUserRESTRICTION_ID(Long userRESTRICTION_ID) {
        this.userRESTRICTION_ID = userRESTRICTION_ID;
    }

    public int getUserIsEnabled() {
        return userIsEnabled;
    }

    public void setUserIsEnabled(int userIsEnabled) {
        this.userIsEnabled = userIsEnabled;
    }

    public Date getUserLastUpdateAt() {
        return userLastUpdateAt;
    }

    public void setUserLastUpdateAt(Date userLastUpdateAt) {
        this.userLastUpdateAt = userLastUpdateAt;
    }

    public Date getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserCreatedAt(Date userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }
}
