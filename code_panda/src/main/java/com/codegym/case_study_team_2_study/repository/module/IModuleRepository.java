package com.codegym.case_study_team_2_study.repository.module;

import com.codegym.case_study_team_2_study.model.module.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface IModuleRepository extends JpaRepository<Module,Integer> {
    @Query(value = "select * from module where module.status = 1 and module.module_name like :searchName",nativeQuery = true)
    Page<Module> findModuleByNameContaining(Pageable pageable,@Param("searchName") String searchName);


    @Transactional
    @Modifying
    @Query(value = "update module set module.status = 0 where module.id = :id",nativeQuery = true)
    void deleteModule(@Param("id") Module module);
}
