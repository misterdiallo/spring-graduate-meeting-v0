package com.linyiuniversity.graduate.microservices.userservices.services.leaders;

import com.linyiuniversity.graduate.microservices.userservices.shared.leaders.LeaderDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;

public interface LeadersService {
    LeaderDTO createLeader(LeaderDTO leaderDTO);
}
