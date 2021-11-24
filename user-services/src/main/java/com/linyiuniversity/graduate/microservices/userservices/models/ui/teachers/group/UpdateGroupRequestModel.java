package com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateGroupRequestModel {
    @NotNull(message = " Group NAME can't be empty.")
    @Size(min = 1, message = "NAME can't be less than 1 characters." )
    private String groupName;

    @Size(min = 10, message = "Description can't be less than 10 characters." )
    private String groupDescription;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
}
