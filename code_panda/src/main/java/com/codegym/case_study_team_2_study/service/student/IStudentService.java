package com.codegym.case_study_team_2_study.service.student;

import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Page<StudentDto> findAllStudent(Pageable pageable,String name);

    void add(Student student);
    List<Student> findAll();

    void delete(Student student);

    Student findById(int id);

    void edit(int id, Student student);
}
