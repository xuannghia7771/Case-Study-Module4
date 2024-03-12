package com.codegym.case_study_team_2_study.service.teacher;

import com.codegym.case_study_team_2_study.dto.teacher.ITeacherDto;
import com.codegym.case_study_team_2_study.model.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeacherService {
    Page<ITeacherDto> searchByName(Pageable pageable, String searchName);

    void saveNewTeacher(Teacher teacher);

    Teacher findById(int id);

    void delete(Teacher teacher);

    List<Teacher> findAll();

    void updateTeacher(int id, Teacher teacher);
}
