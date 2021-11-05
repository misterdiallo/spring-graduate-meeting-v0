package com.linyiuniversity.graduate.microservices.userservices.services.teachers;

import com.linyiuniversity.graduate.microservices.userservices.shared.teachers.TeacherDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;

public interface TeachersService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);
}
