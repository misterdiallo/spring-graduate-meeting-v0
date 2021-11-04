package com.linyiuniversity.graduate.microservices.userservices.data.Users;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity()
@Table(name="users")
public class UserEntity implements Serializable {
    /*
    *
    */
    private static final long serialVersionUID = -3445345300907450984L;


    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; // only Here

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(length = 120, nullable = false)
    private String userName;

    @Column(unique = true, length = 15, nullable = false)
    private String userPhone;

    @Column(unique = true, length = 255, nullable = false)
    private String userEmail;

    @Column(unique = true, length = 18, nullable = false)
    private String userNumber;

    @Column(length = 255, nullable = true)
    private String userWeiXinOpenId;

    @Column(length = 25, nullable = true)
    private String userPolitc;

    @Column(length = 2, nullable = false)
    private String userSex;

    @Column(length = 255, nullable = false)
    private String userEncryptedPassword;

    @Column(nullable = false)
    private Date userLastUpdatedPassword;

    @Column(length = 2, nullable = false)
    private int userIsPhoneVerified;

    @Column(length = 2, nullable = false)
    private int userIsEmailVerified;

    @Column(length = 2, nullable = false)
    private Long userRESTRICTION_ID;

    @Column(length = 2, nullable = false)
    private int userIsEnabled;

    @Column(nullable = false)
    private Date userLastUpdateAt;

    @Column(nullable = false)
    private Date userCreatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
