package com.codegym.case_study_team_2_study.service.module;

import com.codegym.case_study_team_2_study.model.module.Module;
import com.codegym.case_study_team_2_study.repository.module.IModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService implements IModuleService {
    @Autowired
    private IModuleRepository moduleRepository;

    @Override
    public Page<Module> searchByName(Pageable pageable, String searchName) {
        return moduleRepository.findModuleByNameContaining(pageable,"%"+searchName+"%");
    }

    @Override
    public Module findById(int id) {
        return moduleRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Module module) {
        moduleRepository.deleteModule(module);
    }

    @Override
    public void save(Module module) {
        moduleRepository.save(module);
    }

    @Override
    public Module module(int id) {
        return moduleRepository.findById(id).orElse(null);
    }

    @Override
    public void edit(int id, Module module) {
        moduleRepository.save(module);
    }

    @Override
    public List<Module> displayModule() {
        return moduleRepository.findAll();
    }
//    @Override
//    public Page<Module> findAll(Pageable pageable, String searchName) {
//        return moduleRepository.findAll(pageable,searchName);
//    }
}
