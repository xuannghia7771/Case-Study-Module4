package com.codegym.case_study_team_2_study.service.module;

import com.codegym.case_study_team_2_study.model.module.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IModuleService {
    Page<Module> searchByName(Pageable pageable, String searchName);

    Module findById(int id);

    void remove(Module module);

    void save(Module module);

    Module module(int id);

    void edit(int id, Module module);

    List<Module> displayModule();
//    Page<Module> findAll(Pageable , String );
}
