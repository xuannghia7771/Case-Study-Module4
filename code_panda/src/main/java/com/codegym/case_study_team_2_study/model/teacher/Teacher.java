package com.codegym.case_study_team_2_study.model.teacher;

import com.codegym.case_study_team_2_study.model.account.Account;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "teacher_name")
    private String name;
    @Column(name = "teacher_gender")
    private boolean gender;
    @Column(name = "teacher_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    @Column(name = "teacher_salary")
    private double salary;
    @Column(name = "teacher_phone")
    private String phone;
    @Column(name = "teacher_address")
    private String address;
    @Column(name = "teacher_status")
    private boolean status;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Teacher() {

    }

    public Teacher(int id, String name, boolean gender, LocalDate birth, double salary, String phone, String address, boolean status, Account account) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
        this.status = status;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
