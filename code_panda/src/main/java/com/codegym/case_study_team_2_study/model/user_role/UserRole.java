package com.codegym.case_study_team_2_study.model.user_role;

import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.model.role.Role;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public UserRole() {
    }

    public UserRole(Role role, Account account) {
        this.role = role;
        this.account = account;
    }

    public UserRole(int id, Role role, Account account) {
        this.id = id;
        this.role = role;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
