package com.linyiuniversity.graduate.microservices.userservices.services.users.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UsersRepository;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class UsersServiceImplementation implements UsersService {
    UsersRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UsersServiceImplementation(
            UsersRepository usersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
}
