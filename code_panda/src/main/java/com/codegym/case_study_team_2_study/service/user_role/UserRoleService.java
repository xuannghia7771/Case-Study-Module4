package com.codegym.case_study_team_2_study.service.user_role;

import com.codegym.case_study_team_2_study.repository.user_role.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    IUserRoleRepository userRoleRepository;
}
