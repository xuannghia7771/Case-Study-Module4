package com.codegym.case_study_team_2_study.dto.assignment;

import java.sql.Date;
import java.time.LocalDate;

public interface IAssignmentDto {
    String getId();
    Date getEnd();
    Date getStart();
    String getTeacher();
    String getClasses();
    String getPosition();

}
