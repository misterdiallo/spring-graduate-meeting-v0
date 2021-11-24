package com.linyiuniversity.graduate.microservices.userservices.services.teachers.group;

import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupEntity;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupMemberDTO;

public interface GroupService {
    GroupDTO createGroup(GroupDTO groupDTO);

    GroupDTO getGroupDetailsByGroupId(String group_id);
}
