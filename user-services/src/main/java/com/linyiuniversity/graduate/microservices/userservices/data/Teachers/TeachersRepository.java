package com.linyiuniversity.graduate.microservices.userservices.data.Teachers;

import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeachersRepository extends CrudRepository<TeacherEntity, Long> {
    @Query("select s from TeacherEntity s where s.USER_ID = ?1")
    TeacherEntity findByUserId(String userId);
}
