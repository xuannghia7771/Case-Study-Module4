package com.codegym.case_study_team_2_study.model.account;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_email")
    private String email;
    @Column(name = "account_password")
     private  String password;
    @Column(name = "account_status")
    private boolean status;

    public Account() {
    }

    public Account(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Account(int id, String email, String password, boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
