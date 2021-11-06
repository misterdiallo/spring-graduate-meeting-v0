package com.linyiuniversity.graduate.microservices.userservices.services.users;

import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDTO createUser(UserDTO userDetails);

    UserDTO getUserDetailsByUserId(String userName);
    UserDTO login(String login, String password);
}
