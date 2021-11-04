package com.linyiuniversity.graduate.microservices.userservices.data.Users;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

}
