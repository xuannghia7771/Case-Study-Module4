package com.codegym.case_study_team_2_study.model.student;

import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.model.classes.Classes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_gender")
    private boolean gender;
    @Column(name = "student_birth")
    private LocalDate birth;
    @Column(name = "id_card")
    private String idCard;
    @Column(name = "student_phone")
    private String phone;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Classes classes;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Student() {
    }

    public Student(int id, String name, boolean gender, LocalDate birth, String idCard, String phone, boolean status, Classes classes, Account account) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.idCard = idCard;
        this.phone = phone;
        this.status = status;
        this.classes = classes;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
