package com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.impl;

import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupEntity;
import com.linyiuniversity.graduate.microservices.userservices.data.Teachers.group.GroupRepository;
import com.linyiuniversity.graduate.microservices.userservices.data.Users.UserEntity;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.GroupService;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class GroupServiceImplementation implements GroupService {
    GroupRepository groupRepository;

    @Autowired
    public GroupServiceImplementation(
            GroupRepository groupRepository
    ) {
        this.groupRepository = groupRepository;
    }


    @Override
    public GroupDTO createGroup(GroupDTO groupDTO) {

        groupDTO.setLastUpdateAt(Date.from(Instant.now()));
        groupDTO.setCreatedAt(Date.from(Instant.now()));
        groupDTO.setGroupLink(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        GroupEntity groupEntity = modelMapper.map(groupDTO, GroupEntity.class);

        groupRepository.save(groupEntity);

        GroupDTO returnValue = modelMapper.map(groupEntity, GroupDTO.class);
        return returnValue;
    }

    @Override
    public GroupDTO getGroupDetailsByGroupId(String group_id) {
        GroupEntity entity = groupRepository.getById(group_id);
        if(entity == null) throw new UsernameNotFoundException(group_id);
        return new ModelMapper().map(entity, GroupDTO.class);
    }
}
