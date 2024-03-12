package com.codegym.case_study_team_2_study.service.teacher;

import com.codegym.case_study_team_2_study.dto.teacher.ITeacherDto;
import com.codegym.case_study_team_2_study.model.student.Student;
import com.codegym.case_study_team_2_study.model.teacher.Teacher;
import com.codegym.case_study_team_2_study.repository.teacher.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public Page<ITeacherDto> searchByName(Pageable pageable, String searchName) {
        return teacherRepository.findTeacherByNameContaining(pageable, "%" + searchName + "%");
    }

    @Override
    public void saveNewTeacher(Teacher teacher) {
        teacherRepository.saveNewTeacher(teacher.getName(), teacher.isGender(), teacher.getBirth(),
                teacher.getSalary(), teacher.getPhone(), teacher.getAddress());
    }

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.deleteTeacher(teacher);
    }


    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void updateTeacher(int id, Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
