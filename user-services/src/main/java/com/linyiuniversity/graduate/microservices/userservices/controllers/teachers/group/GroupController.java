package com.linyiuniversity.graduate.microservices.userservices.controllers.teachers.group;

import com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.CreateGroupRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.GroupResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.TeachersService;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.GroupService;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupDTO;
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
@RequestMapping("/teachers/group")
public class GroupController {
    @Autowired
    private Environment env;
    @Autowired
    TeachersService teachersService;

    @Autowired
    UsersService usersService;
    @Autowired
    GroupService groupService;

    //    Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {
        return "users/TEACHERS/GROUP services working on port: " + env.getProperty("local.server.port") ;
    }

    @PostMapping()
    public ResponseEntity<GroupResponseModel> createGroup(@Valid @RequestBody CreateGroupRequestModel createGroupRequestModel) throws IllegalAccessException {
        UserDTO user = usersService.getUserDetailsByUserId(createGroupRequestModel.getTeacher_id());
        String role = usersService.getRole(createGroupRequestModel.getTeacher_id());
        if (role != "teacher") throw new IllegalAccessException("ERROR001ACCESS : The current user is not allowed to perform this action. Please login in with the authorized user credentials.");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        GroupDTO groupDTO = modelMapper.map(createGroupRequestModel, GroupDTO.class);
        GroupDTO createGroup = groupService.createGroup(groupDTO);

        GroupResponseModel responseModel = modelMapper.map(createGroup, GroupResponseModel.class);
        System.out.println("Group created with success!");
        System.out.println(responseModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}

