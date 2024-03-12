package com.codegym.case_study_team_2_study.dto.teacher;

import java.time.LocalDate;

public interface ITeacherDto {
    int getId();
    String getName();
    boolean isGender();
    LocalDate getBirth();
    double getSalary();
    String getPhone();
    String getAddress();
}
