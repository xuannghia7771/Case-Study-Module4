package com.codegym.case_study_team_2_study.service.classes;

import com.codegym.case_study_team_2_study.dto.classes.ListClassesDto;
import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClassesService {
    List<ListClassesDto> findAllClass(int id);

    List<Classes> findAll();

    void add(Classes classes);

    void delete(Classes classes);

    Page<Classes> findClass(Pageable pageable, String name);

    Classes findById(int id);

    Page<StudentDto> findStudent(Pageable pageable, int idClass);

}
