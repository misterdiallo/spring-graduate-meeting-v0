package com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group;

import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeacherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
   GroupEntity getById(String id);
}
