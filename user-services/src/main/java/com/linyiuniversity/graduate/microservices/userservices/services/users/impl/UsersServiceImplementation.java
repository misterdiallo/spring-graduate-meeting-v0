package com.linyiuniversity.graduate.microservices.userservices.services.users.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeaderEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeadersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentsRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeacherEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeachersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UsersRepository;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class UsersServiceImplementation implements UsersService {
    UsersRepository userRepository;
    StudentsRepository studentsRepository;
    LeadersRepository leadersRepository;
    TeachersRepository teachersRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UsersServiceImplementation(
            UsersRepository usersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            StudentsRepository studentsRepository,
            LeadersRepository leadersRepository,
            TeachersRepository teachersRepository
    ) {
        this.userRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.studentsRepository = studentsRepository;
        this.leadersRepository = leadersRepository;
        this.teachersRepository = teachersRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setUserEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getUserPassword()));
        userDetails.setUserLastUpdatedPassword(Date.from(Instant.now()));
        userDetails.setUserLastUpdateAt(Date.from(Instant.now()));
        userDetails.setUserCreatedAt(Date.from(Instant.now()));
        userDetails.setUserIsPhoneVerified(1);
        userDetails.setUserIsEmailVerified(1);
        userDetails.setUserRESTRICTION_ID(00000L);
        userDetails.setUserIsEnabled(1);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        userRepository.save(userEntity);

        UserDTO returnValue = modelMapper.map(userEntity, UserDTO.class);
        return returnValue;
    }

    @Override
    public UserDTO getUserDetailsByUserId(String userId) {
        UserEntity entity = userRepository.findByUserId(userId);
        if(entity == null) throw new UsernameNotFoundException(userId);
        return new ModelMapper().map(entity, UserDTO.class);
    }

    @Override
    public UserDTO login(String login, String password) {

        UserEntity userLogged = userRepository.findByUserIdOrUserNumberOrUserEmailOrUserPhone(login,login,login,login);
        if(userLogged == null) throw new UsernameNotFoundException(login);

        boolean isPasswordMatch  = bCryptPasswordEncoder.matches(password, userLogged.getUserEncryptedPassword());
        if (!isPasswordMatch) {
            throw new UsernameNotFoundException(login);
        } else {
            StudentEntity student = studentsRepository.findByUserId(userLogged.getUserId());
            TeacherEntity teacher = teachersRepository.findByUserId(userLogged.getUserId());
            LeaderEntity leader = leadersRepository.findByUserId(userLogged.getUserId());
            if (student != null) {
                return new ModelMapper().map(userLogged, UserDTO.class);
            } else if (teacher != null) {
                return new ModelMapper().map(userLogged, UserDTO.class);
            } else if (leader != null) {
                return new ModelMapper().map(userLogged, UserDTO.class);
            }else {
                throw new UsernameNotFoundException(login);
            }


        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUserIdOrUserNumberOrUserEmailOrUserPhone(s,s,s,s);
        if(entity == null) throw new UsernameNotFoundException(s);

        return new User(
                entity.getUserId(),
                entity.getUserEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
