package com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupMemberEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupMemberRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.GroupMemberService;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.GroupService;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupMemberDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Service
public class GroupMemberServiceImplementation implements GroupMemberService {
    GroupMemberRepository groupMemberRepository;

    @Autowired
    public GroupMemberServiceImplementation(
            GroupMemberRepository groupMemberRepository
    ) {
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public GroupMemberDTO createGroupMember(GroupMemberDTO groupMemberDTO) {
        groupMemberDTO.setLastUpdateAt(Date.from(Instant.now()));
        groupMemberDTO.setCreatedAt(Date.from(Instant.now()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        GroupMemberEntity groupMemberEntity  = modelMapper.map(groupMemberDTO, GroupMemberEntity.class);

        groupMemberRepository.save(groupMemberEntity);

        GroupMemberDTO returnValue = modelMapper.map(groupMemberEntity, GroupMemberDTO.class);
        return returnValue;
    }

    @Override
    public String checkIfMemberIsInAGroup(String group_id, String user_id) {
        GroupMemberEntity entity = groupMemberRepository.findByUserIdAndGroupId(user_id,group_id);
        if(entity == null) {
            return  "1"; //"Not in the Group yet"
        }
        else {
            return "0"; //"Already In the Group"
        }
    }
}
