package com.linyiuniversity.graduate.microservices.userservices.controllers.students;


import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.services.students.StudentsService;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private Environment env;
    @Autowired
    StudentsService studentsService;

    @Autowired
    UsersService usersService;


//    Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {

        return "users/STUDENTS services working on port: " + env.getProperty("local.server.port") ;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTO = modelMapper.map(createUserRequestModel, UserDTO.class);
        UserDTO createUser = usersService.createUser(userDTO);

        CreateUserResponseModel responseModel = modelMapper.map(createUser, CreateUserResponseModel.class);

        //Creating specific user( Student, Teacher, LinDao,..)
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setUSER_ID(responseModel.getUserId());
        StudentDTO createStudent = studentsService.createStudent(studentDTO);
        System.out.println("Student created with success!");
        System.out.println("Student created with success!");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }






}
