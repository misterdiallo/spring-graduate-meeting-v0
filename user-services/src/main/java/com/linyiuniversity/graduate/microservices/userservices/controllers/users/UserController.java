package com.linyiuniversity.graduate.microservices.userservices.controllers.users;

import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.LoginUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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



//    Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {
        return "USERS services working on port: " + env.getProperty("local.server.port") ;
    }

    @RequestMapping("/login")
    @PostMapping()
    public ResponseEntity<CreateUserResponseModel> logIn(@Valid @RequestBody LoginUserRequestModel loginUserRequestModel) {

        UserDTO user = usersService.login(loginUserRequestModel.getLogin().toString(),loginUserRequestModel.getPassword());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CreateUserResponseModel responseModel = modelMapper.map(user, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.FOUND).body(responseModel);
    }
}
