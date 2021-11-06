package com.linyiuniversity.graduate.microservices.userservices.data.Students;

import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<StudentEntity, Long> {
    @Query("select s from StudentEntity s where s.USER_ID = ?1")
    StudentEntity findByUserId(String userId);
}
