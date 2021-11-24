package com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.groupMember;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateGroupMemberRequestModel {
    @NotNull(message = " Alias  can't be empty.")
    @Size(min = 1, message = "Alias can't be less than 1 characters." )
    private String userAlias;

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
