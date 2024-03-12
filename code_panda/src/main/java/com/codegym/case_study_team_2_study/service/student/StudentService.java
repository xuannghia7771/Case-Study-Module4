package com.codegym.case_study_team_2_study.service.student;

import com.codegym.case_study_team_2_study.dto.student.ListStudentDto;
import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.student.Student;
import com.codegym.case_study_team_2_study.repository.student.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository iStudentRepository;
    @Override
    public Page<StudentDto> findAllStudent(Pageable pageable, String name) {
        return iStudentRepository.findAllStudent(pageable,"%" + name + "%");
    }

    @Override
    public void add(Student student) {
        iStudentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public void delete(Student student) {
        iStudentRepository.deleteId(student);
    }

    @Override
    public Student findById(int id) {
        return iStudentRepository.findById(id).orElse(null);
    }

    @Override
    public void edit(int id,Student student) {
        iStudentRepository.save(student);
    }
}
