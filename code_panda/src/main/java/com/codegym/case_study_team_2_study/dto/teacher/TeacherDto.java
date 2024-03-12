package com.codegym.case_study_team_2_study.dto.teacher;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TeacherDto implements Validator {
    private int id;
    private String name;
    private boolean gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private double salary;
    private String phone;
    private String address;

    public TeacherDto() {
    }

    public TeacherDto(int id, String name, boolean gender, LocalDate birth, double salary, String phone, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherDto teacherDto = (TeacherDto) target;
        LocalDate date = LocalDate.now();
        if (teacherDto.getName().equals("")){
            errors.rejectValue("name",null,"Không được để trống!");
        } else if (!teacherDto.getName().matches("^(([A-Z][a-z]+) )+([A-Z][a-z]+){1}$")){
            errors.rejectValue("name",null,"Tên phải viết hoa chữ cái đầu và mỗi từ phải cách nhau bởi khoảng trắng !");
        }
        if (teacherDto.getPhone().equals("")){
            errors.rejectValue("phone",null,"Không được để trống!");
        } else if (!teacherDto.getPhone().matches("^0[0-9]{9,11}$")){
            errors.rejectValue("phone",null,"Số điện thoại phải đúng định dạng (0)xxxxxxxxx hoặc (0)xxxxxxxxxxx với x là các số từ (0-9)");
        }
        if (teacherDto.getAddress().equals("")){
            errors.rejectValue("address",null,"Không được để trống!");
        } else if (!teacherDto.getAddress().matches("^[0-9]{1,4} (([A-Z][a-z]+) )+([A-Z][a-z]+){1}$")){
            errors.rejectValue("address",null,"Địa chỉ phải bắt đầu với số nhà và tên đường\nVD: 123 Dien Bien Phu");
        }
    }
}
