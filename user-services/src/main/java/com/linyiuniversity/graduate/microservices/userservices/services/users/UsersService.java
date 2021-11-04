package com.linyiuniversity.graduate.microservices.userservices.services.users;

import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;

public interface UsersService {
    UserDTO createUser(UserDTO userDetails);
}
