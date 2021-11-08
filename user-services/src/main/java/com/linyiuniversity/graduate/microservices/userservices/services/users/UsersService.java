package com.linyiuniversity.graduate.microservices.userservices.services.users;

import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserWithRoleDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDTO createUser(UserDTO userDetails);

    UserDTO getUserDetailsByUserId(String userName);
    UserWithRoleDTO login(String login, String password);
    String getRole(String userId);
}
