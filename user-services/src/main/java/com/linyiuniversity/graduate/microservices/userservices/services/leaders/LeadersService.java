package com.linyiuniversity.graduate.microservices.userservices.services.leaders;

import com.linyiuniversity.graduate.microservices.userservices.shared.leaders.LeaderDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LeadersService extends UserDetailsService {
    LeaderDTO createLeader(LeaderDTO leaderDTO);
}
