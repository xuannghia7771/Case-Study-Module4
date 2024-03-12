package com.codegym.case_study_team_2_study.dto.account;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountDto implements Validator {
    private String email;
    private  String password;

    public AccountDto() {
    }

    public AccountDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDto accountDto = (AccountDto) target;
        if (accountDto.getEmail().equals("")){
            errors.rejectValue("email", null, "Không được để trống!");
        } else if (!accountDto.getEmail().matches("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errors.rejectValue("email", null, "Không đúng định dạng!");
        }
        if (accountDto.getPassword().equals("")){
            errors.rejectValue("password", null, "Không được để trống!");
        }
    }
}
