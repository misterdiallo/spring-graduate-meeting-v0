package com.linyiuniversity.graduate.microservices.userservices.services.students;

import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;
import com.linyiuniversity.graduate.microservices.userservices.shared.users.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface StudentsService {
    StudentDTO createStudent(StudentDTO studentDTO);

}
