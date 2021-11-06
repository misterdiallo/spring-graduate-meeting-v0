package com.linyiuniversity.graduate.microservices.userservices.data.Users;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);
    UserEntity findByUserIdAndUserEncryptedPassword(String userId, String password);

    UserEntity findByUserIdOrUserNumberOrUserEmailOrUserPhone(String s, String s0, String s1, String s2);
}
