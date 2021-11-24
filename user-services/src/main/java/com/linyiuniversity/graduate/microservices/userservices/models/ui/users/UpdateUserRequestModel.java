package com.linyiuniversity.graduate.microservices.userservices.models.ui.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserRequestModel {

    @NotNull(message = "NAME can't be empty.")
    @Size(min = 2, message = "NAME can't less than 2 characters." )
    private String userName;

    @NotNull(message = "PHONE can't be empty.")
    @Size(min = 11, message = "PHONE can't less than 11 characters." )
    private String userPhone;

    @NotNull(message = "EMAIL can't be empty.")
    @Email(message = "EMAIL format is incorrect")
    private String userEmail;

    @NotNull(message = "USER NUMBER can't be empty.")
    @Size(min = 6, message = "USER NUMBER can't less than 6 characters." )
    private String userNumber;

    private String userWeiXinOpenId;

    private String userPolitc;

    @NotNull(message = "SEX can't be empty.")
    @Size(min = 1, message = "SEX can't less than 1 characters." )
    private String userSex;

    @NotNull(message = "PASSWORD can't be empty.")
    @Size(min = 6, message = "PASSWORD can't less than 6 characters." )
    private String userPassword;



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


}
