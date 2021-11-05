package com.linyiuniversity.graduate.microservices.userservices.services.students;

import com.linyiuniversity.graduate.microservices.userservices.shared.students.StudentDTO;

public interface StudentsService {
    StudentDTO createStudent(StudentDTO studentDTO);
}
