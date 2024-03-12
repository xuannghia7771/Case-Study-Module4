package com.codegym.case_study_team_2_study.dto.student;

import java.time.LocalDate;

public interface StudentDto {
    int getStudentId();
    int getClassId();
    String getClassName();
    LocalDate getStudentBirth();

    boolean getStudentGender();

    String getStudentIdCard();

    String getStudentName();
    boolean getStudentStatus();

    String getStudentPhone();
}
