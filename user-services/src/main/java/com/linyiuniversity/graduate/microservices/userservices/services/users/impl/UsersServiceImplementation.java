package com.linyiuniversity.graduate.microservices.userservices.services.users.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeaderEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeadersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Students.StudentsRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeacherEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeachersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UsersRepository;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.CreateUserResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.users.UpdateUserRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserWithRoleDTO;
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
    public UserWithRoleDTO login(String login, String password) {

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
                UserWithRoleDTO userWithRoleDTO = new ModelMapper().map(userLogged, UserWithRoleDTO.class);
                userWithRoleDTO.setRole("student");
                return new ModelMapper().map(userWithRoleDTO, UserWithRoleDTO.class);
            } else if (teacher != null) {
                UserWithRoleDTO userWithRoleDTO = new ModelMapper().map(userLogged, UserWithRoleDTO.class);
                userWithRoleDTO.setRole("teacher");
                return new ModelMapper().map(userWithRoleDTO, UserWithRoleDTO.class);
            } else if (leader != null) {
                UserWithRoleDTO userWithRoleDTO = new ModelMapper().map(userLogged, UserWithRoleDTO.class);
                userWithRoleDTO.setRole("leader");
                return new ModelMapper().map(userWithRoleDTO, UserWithRoleDTO.class);
            }else {
                throw new UsernameNotFoundException(login);
            }
        }
    }

    @Override
    public String getRole(String userId) {
        StudentEntity student = studentsRepository.findByUserId(userId);
        TeacherEntity teacher = teachersRepository.findByUserId(userId);
        LeaderEntity leader = leadersRepository.findByUserId(userId);
        if (student != null) {
            return "student";
        } else if (teacher != null) {
            return "teacher";
        } else if (leader != null) {
            return "leader";
        }else {
            return "none";
        }
    }

    @Override
    public UserDTO UpdateUser(String userId, UpdateUserRequestModel updateUserRequestModel) {
//        UserEntity oldEntity = userRepository.findByUserId(userId);
//        oldEntity.setUserLastUpdateAt(Date.from(Instant.now()));
//
//        oldEntity.setUserName(updateUserRequestModel.getUserName());
//        oldEntity.setUserPhone(updateUserRequestModel.getUserPhone());
//        oldEntity.setUserEmail(updateUserRequestModel.getUserEmail());
//        oldEntity.setUserNumber(updateUserRequestModel.getUserNumber());
//        oldEntity.setUserSex(updateUserRequestModel.getUserSex());
//
//        UserDTO userDTO;
//        userRepository.
//        oldEntity.setUser(updateUserRequestModel.getUser());
//        oldEntity.setUser(updateUserRequestModel.getUser());
//        oldEntity.setUser(updateUserRequestModel.getUser());
//
//        UserRest oldData = users.get(userId);
//        oldData.setFirstName(userUpdateRequestModel.getFirstName());
//        oldData.setLastName(userUpdateRequestModel.getLastName());
//        oldData.setEmail(userUpdateRequestModel.getEmail());
//        oldData.setPassword(userUpdateRequestModel.getPassword());
//        users.put(userId, oldData);
//        return oldData;
        return null;
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
