package com.linyiuniversity.graduate.microservices.userservices.services.leaders.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeaderEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Leaders.LeadersRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeacherEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.TeachersRepository;
import com.linyiuniversity.graduate.microservices.userservices.services.leaders.LeadersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.leaders.LeaderDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LeadersServiceImplementation implements LeadersService {
    LeadersRepository leadersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public LeadersServiceImplementation(
            LeadersRepository leadersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.leadersRepository = leadersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public LeaderDTO createLeader(LeaderDTO leaderDTO ) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LeaderEntity leaderEntity  = modelMapper.map(leaderDTO, LeaderEntity.class);

        leadersRepository.save(leaderEntity);

        LeaderDTO returnValue = modelMapper.map(leaderEntity, LeaderDTO.class);
        return returnValue;
    }
}
