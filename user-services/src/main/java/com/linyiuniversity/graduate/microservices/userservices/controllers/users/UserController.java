package com.linyiuniversity.graduate.microservices.userservices.controllers.users;

import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.*;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserWithRoleDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private Environment env;

    @Autowired
    UsersService usersService;

    // Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {
        return "USERS services working on port: " + env.getProperty("local.server.port") ;
    }

    // Login manually, but it doesnt give a token
    @RequestMapping("/loginManual")
    @PostMapping()
    public ResponseEntity<LoginUserResponseModel> logIn(@Valid @RequestBody LoginUserRequestModel loginUserRequestModel) {
        UserWithRoleDTO userWithRoleDTO = usersService.login(loginUserRequestModel.getLogin().toString(),loginUserRequestModel.getPassword());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LoginUserResponseModel responseModel = modelMapper.map(userWithRoleDTO, LoginUserResponseModel.class);
        // TODO : Implement a token and give the token back to response

        return ResponseEntity.status(HttpStatus.FOUND).body(responseModel);
    }

    // Get one User with his/her role
    @GetMapping( path = "/{userId}")
    public LoginUserResponseModel getUser(@PathVariable String userId) {
        UserDTO user = usersService.getUserDetailsByUserId(userId);
        String role = usersService.getRole(userId);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LoginUserResponseModel responseModel = modelMapper.map(user, LoginUserResponseModel.class);
        responseModel.setRole(role);
        return responseModel;
    }

    // Update a user
    @PutMapping(path = "/{userId}" )
    public LoginUserResponseModel putUser(
            @Valid
            @PathVariable String userId,
            @Valid @RequestBody UpdateUserRequestModel updateUserRequestModel
    ) {
        UserDTO userUpdated = usersService.UpdateUser(userId, updateUserRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LoginUserResponseModel responseModel = modelMapper.map(userUpdated, LoginUserResponseModel.class);
        return responseModel;
    }










    /*
            **** From here: all the requests require a valid login from user
    */
    // Check the status, if the microservice is working

}
