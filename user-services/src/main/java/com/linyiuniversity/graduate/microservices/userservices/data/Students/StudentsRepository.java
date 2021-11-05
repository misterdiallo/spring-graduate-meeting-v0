package com.linyiuniversity.graduate.microservices.userservices.data.Students;

import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<StudentEntity, Long> {

}
