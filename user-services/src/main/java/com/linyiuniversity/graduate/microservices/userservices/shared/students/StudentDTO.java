package com.linyiuniversity.graduate.microservices.userservices.shared.students;

import java.io.Serializable;
import java.util.Date;

public class StudentDTO implements Serializable {
    /*
     *
     */
    private static final long serialVersionUID = -49395859493945954L;

    // only Here
    private String USER_ID;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
