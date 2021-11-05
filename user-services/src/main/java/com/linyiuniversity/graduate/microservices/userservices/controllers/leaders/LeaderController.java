package com.linyiuniversity.graduate.microservices.userservices.controllers.leaders;

import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.services.leaders.LeadersService;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.TeachersService;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.leaders.LeaderDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/leaders")
public class LeaderController {
    @Autowired
    private Environment env;
    @Autowired
    LeadersService leadersService;

    @Autowired
    UsersService usersService;

//    Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {
        return "users/LEADERS services working on port: " + env.getProperty("local.server.port") ;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTO = modelMapper.map(createUserRequestModel, UserDTO.class);
        UserDTO createUser = usersService.createUser(userDTO);

        CreateUserResponseModel responseModel = modelMapper.map(createUser, CreateUserResponseModel.class);

        //Creating specific user( Student, Teacher, LinDao,..)
        LeaderDTO leaderDTO   = new LeaderDTO();
        leaderDTO.setUSER_ID(responseModel.getUserId());
        LeaderDTO createLeader = leadersService.createLeader(leaderDTO);
        System.out.println("Leader created with success!");
        System.out.println(createLeader);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }






}
