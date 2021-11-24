package com.linyiuniversity.graduate.microservices.userservices.services.teachers.group;

import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupMemberDTO;

public interface GroupMemberService {
    GroupMemberDTO createGroupMember(GroupMemberDTO groupMemberDTO);

    String checkIfMemberIsInAGroup(String group_id, String user_id);
}
