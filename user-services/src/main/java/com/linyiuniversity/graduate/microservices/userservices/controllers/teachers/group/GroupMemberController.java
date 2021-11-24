package com.linyiuniversity.graduate.microservices.userservices.controllers.teachers.group;

import com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.CreateGroupRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.GroupResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.groupMember.CreateGroupMemberRequestModel;
import com.linyiuniversity.graduate.microservices.userservices.models.ui.teachers.group.groupMember.GroupMemberResponseModel;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.TeachersService;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.GroupMemberService;
import com.linyiuniversity.graduate.microservices.userservices.services.teachers.group.GroupService;
import com.linyiuniversity.graduate.microservices.userservices.services.users.UsersService;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.group.GroupMemberDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/teachers/group/members")
public class GroupMemberController {
    @Autowired
    private Environment env;
    @Autowired
    TeachersService teachersService;
    @Autowired
    GroupService groupService;
    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    UsersService usersService;

    //    Check the status, if the microservice is working
    @GetMapping(path = "/check/status")
    public String CheckStatus() {
        return "users/TEACHERS/GROUP/Member services working on port: " + env.getProperty("local.server.port") ;
    }

    @PostMapping()
    public ResponseEntity<GroupMemberResponseModel> createGroupMember(@Valid @RequestBody CreateGroupMemberRequestModel createGroupMemberRequestModel) throws IllegalAccessError {
        // if the group where you want to add member exist.
        GroupDTO group = groupService.getGroupDetailsByGroupId(createGroupMemberRequestModel.getGroup_id());
        // if the user you want to add as a member exist.
        UserDTO user = usersService.getUserDetailsByUserId(createGroupMemberRequestModel.getUser_id());
        // if the user you want to add is not already a member of the group.
        String existOrNot = groupMemberService.checkIfMemberIsInAGroup(createGroupMemberRequestModel.getGroup_id(), createGroupMemberRequestModel.getUser_id());
        if (existOrNot == "0") throw new IllegalAccessError("Error 001 GroupMember : The user you want to add is already a member the Group.");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        GroupMemberDTO groupMemberDTO = modelMapper.map(createGroupMemberRequestModel, GroupMemberDTO.class);
        GroupMemberDTO createGroupMember = groupMemberService.createGroupMember(groupMemberDTO);

        GroupMemberResponseModel responseModel = modelMapper.map(createGroupMember, GroupMemberResponseModel.class);
        System.out.println("Group Member created with success!");
        System.out.println(responseModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}
