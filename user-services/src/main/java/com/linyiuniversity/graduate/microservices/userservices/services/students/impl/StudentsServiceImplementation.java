package com.linyiuniversity.graduate.microservices.userservices.services.students.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentsRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UsersRepository;
import com.linyiuniversity.graduate.microservices.userservices.services.students.StudentsService;
import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class StudentsServiceImplementation implements StudentsService {
    StudentsRepository studentsRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public StudentsServiceImplementation(
            StudentsRepository studentsRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
//        this.userRepository = usersRepository;
        this.studentsRepository = studentsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        studentsRepository.save(studentEntity);
        StudentDTO returnValue = modelMapper.map(studentEntity, StudentDTO.class);
        return returnValue;
    }

    @Override
    public StudentDTO getStudent(String userId) {
        StudentEntity entity = studentsRepository.findByUserId(userId);
        if(entity == null) throw new UsernameNotFoundException(userId);
        return new ModelMapper().map(entity, StudentDTO.class);
    }


}
