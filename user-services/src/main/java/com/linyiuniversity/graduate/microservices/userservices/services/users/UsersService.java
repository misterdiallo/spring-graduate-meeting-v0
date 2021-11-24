package com.linyiuniversity.graduate.microservices.userservices.services.users;

import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.LoginUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.UpdateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserWithRoleDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    // create a new user
    UserDTO createUser(UserDTO userDetails);
    // get user details using his/her username
    UserDTO getUserDetailsByUserId(String userName);
    // login user
    UserWithRoleDTO login(String login, String password);
    // get the user role name
    String getRole(String userId);

    UserDTO UpdateUser(String userId, UpdateUserRequestModel updateUserRequestModel);
}
