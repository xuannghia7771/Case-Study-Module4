package com.codegym.case_study_team_2_study.service.classes;

import com.codegym.case_study_team_2_study.dto.classes.ClassDto;
import com.codegym.case_study_team_2_study.dto.classes.ListClassesDto;
import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import com.codegym.case_study_team_2_study.repository.classes.IClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ClassesService implements IClassesService {
    @Autowired
    private IClassesRepository iClassesRepository;

    @Override
    public List<ListClassesDto> findAllClass(int id) {
        return iClassesRepository.findAllClass(id);
    }

    @Override
    public List<Classes> findAll() {
        return iClassesRepository.findAll();
    }

    @Override
    public void add(Classes classes) {

        iClassesRepository.save(classes);
    }

    @Override
    public void delete(Classes classes) {
        iClassesRepository.deleteClass(classes);
    }

    @Override
    public Page<Classes> findClass(Pageable pageable, String name) {
        return iClassesRepository.listClass(pageable, "%" + name + "%");
    }

    @Override
    public Classes findById(int id) {
        return iClassesRepository.findById(id).orElse(null);
    }

    @Override
    public Page<StudentDto> findStudent(Pageable pageable, int idClass){
        return iClassesRepository.listStudent(pageable, idClass);
    }


}
