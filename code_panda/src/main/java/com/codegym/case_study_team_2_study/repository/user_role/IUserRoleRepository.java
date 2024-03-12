package com.codegym.case_study_team_2_study.repository.user_role;

import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.model.user_role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByAccount(Account account);
}
