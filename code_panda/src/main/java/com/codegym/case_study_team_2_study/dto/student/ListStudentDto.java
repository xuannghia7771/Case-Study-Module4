package com.codegym.case_study_team_2_study.dto.student;

import com.codegym.case_study_team_2_study.model.classes.Classes;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;


public class ListStudentDto implements Validator {
    private int id;

    private String name;
    private boolean gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String idCard;

    private String phone;
    private Classes classes;

    public ListStudentDto() {
    }

    public ListStudentDto(int id, String name, boolean gender, LocalDate birth, String idCard, String phone, Classes classes) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.idCard = idCard;
        this.phone = phone;
        this.classes = classes;
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ListStudentDto studentDto = (ListStudentDto) target;
        LocalDate date = LocalDate.now();
        LocalDate birthDate = LocalDate.ofEpochDay(studentDto.getBirth().toEpochDay());
        Period age = Period.between(birthDate, date);
        if (age.getYears()<10){
            errors.rejectValue("birth", "Invalid", "Ngày sinh phải lớn hơn 10 tuổi");
        } else if (studentDto.getBirth() != null) {
            if (studentDto.getBirth().isAfter(date)) {
                errors.rejectValue("birth", "Invalid", "Ngày sinh không thể sau ngày hiện tại");
            } else if (studentDto.getBirth().getYear() < 1980) {
                errors.rejectValue("birth", "Invalid", "Ngày sinh không hợp lệ");
            }
        }
        if (studentDto.getName().equals("")) {
            errors.rejectValue("name", null, "Không được để trống!");
        }
        if (studentDto.getPhone().equals("")) {
            errors.rejectValue("phone", null, "Không được để trống!");
        } else if (!studentDto.getPhone().matches("^0[0-9]{9,11}$")) {
            errors.rejectValue("phone", null, "Số điện thoại phải đúng định dạng (0)xxxxxxxxx hoặc (0)xxxxxxxxxxx với x là các số từ (0-9)");
        }
        if (studentDto.getIdCard().equals("")) {
            errors.rejectValue("idCard", null, "Không được để trống!");
        } else if (!studentDto.getPhone().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("idCard", null, "Số CMND là các số từ (0-9) có chiều dài là 9 đến 12 số ");
        }
    }
}


