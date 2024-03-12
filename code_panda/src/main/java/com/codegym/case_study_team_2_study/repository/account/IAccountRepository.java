package com.codegym.case_study_team_2_study.repository.account;

import com.codegym.case_study_team_2_study.dto.account.IAccountDto;
import com.codegym.case_study_team_2_study.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    //    @Query(value = "select * from account where account_status = true", nativeQuery = true)
    @Query(value = "select account_id as id, account_email as email, role_name as roleName " +
            "from account " +
            "join user_role on account.id = user_role.account_id " +
            "join role on user_role.role_id = role.id " +
            "where account.account_status = true " +
            "and account_email like :keyword", nativeQuery = true)
    Page<IAccountDto> findAccountByEmailContaining(Pageable pageable, @Param("keyword") String keyword);
    @Modifying
    @Transactional
//    @Query(value = "update account set account.account_status = false where account.id = :id", nativeQuery = true)
    @Query(value = "call disableAccount(:id);", nativeQuery = true)
    void deleteAccount(@Param("id") int deleteId);

    Account findAccountByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "call add_user(:p_email, :p_password, :p_status)", nativeQuery = true)
    void addAccount(@Param("p_email") String email, @Param("p_password") String password, @Param("p_status") boolean status);
    @Modifying
    @Transactional
    @Query(value = "call add_account(:p_email, :p_password, :p_status, :p_role)", nativeQuery = true)
    void createAccount(@Param("p_email")String email,@Param("p_password") String newPass, @Param("p_status")boolean status,@Param("p_role") int role);
}
