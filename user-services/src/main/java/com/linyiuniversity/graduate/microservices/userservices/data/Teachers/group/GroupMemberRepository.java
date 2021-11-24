package com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupMemberRepository extends CrudRepository<GroupMemberEntity, Long> {
   GroupMemberEntity getById(String id);

    @Query("select s from GroupMemberEntity s where s.user_id = ?1 and s.group_id = ?2 ")
    GroupMemberEntity findByUserIdAndGroupId(String user_id, String group_id);
}
