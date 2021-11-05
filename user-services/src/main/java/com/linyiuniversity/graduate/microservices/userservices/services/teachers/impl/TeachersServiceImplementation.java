package com.linyiuniversity.graduate.microservices.userservices.services.teachers.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeacherEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeachersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UsersRepository;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.TeachersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class TeachersServiceImplementation implements TeachersService {
    TeachersRepository teachersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public TeachersServiceImplementation(
            TeachersRepository teachersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.teachersRepository = teachersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TeacherEntity teacherEntity = modelMapper.map(teacherDTO, TeacherEntity.class);

        teachersRepository.save(teacherEntity);

        TeacherDTO returnValue = modelMapper.map(teacherEntity, TeacherDTO.class);
        return returnValue;
    }
}
