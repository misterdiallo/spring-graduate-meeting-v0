package com.linyiuniversity.graduate.microservices.userservices.controllers.students;


import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.LoginUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.services.students.StudentsService;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private Environment env;
    @Autowired
    StudentsService studentsService;

    @Autowired
    UsersService usersService;

    // Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {
        return "users/STUDENTS services working on port: " + env.getProperty("local.server.port") ;
    }





    // Create a student
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
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    /*
     **** From here: all the requests require a valid login from the student
     */
    // Check the status, if the microservice is working
//    @GetMapping(path = "/home")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
//    public String HomeStudent(@AuthenticationPrincipal UsersServiceImplementation usersServiceImplementation) {
//        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//
//        System.out.println(usersServiceImplementation);
//        return "USERS services working on port: " + env.getProperty("local.server.port") ;
//    }






}
