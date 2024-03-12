package com.codegym.case_study_team_2_study.service.account;

import com.codegym.case_study_team_2_study.dto.account.IAccountDto;
import com.codegym.case_study_team_2_study.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<IAccountDto> searchByEmail(Pageable pageable, String keyword);

    void deleteAccount(int deleteId);

    void addAccount(Account account);

    Account findByEmail(String email);

    void change(Account account);
    boolean testPass(String username, String pass);
    public void changePass(String username, String newPassword);

    void sendEmail(String to, String subject, String body);

    String sendEmailAndReturnCode(String to);

    void forgotPassword(Account account);

    void createAccount(Account newAccount, int role);
}
