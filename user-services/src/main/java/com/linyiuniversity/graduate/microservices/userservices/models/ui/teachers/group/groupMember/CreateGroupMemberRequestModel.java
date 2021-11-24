package com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.groupMember;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateGroupMemberRequestModel {
    private String user_id;
    private String group_id;
    private String userAlias;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
