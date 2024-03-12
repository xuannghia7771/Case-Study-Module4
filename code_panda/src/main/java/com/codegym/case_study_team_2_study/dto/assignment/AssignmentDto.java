package com.codegym.case_study_team_2_study.dto.assignment;

import com.codegym.case_study_team_2_study.model.classes.Classes;
import com.codegym.case_study_team_2_study.model.position.Position;
import com.codegym.case_study_team_2_study.model.teacher.Teacher;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

public class AssignmentDto {
    private int id;
    private Classes classes;
    private Teacher teacher;
    private Position position;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    public AssignmentDto(int id, Classes classes, Teacher teacher, Position position, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.classes = classes;
        this.teacher = teacher;
        this.position = position;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AssignmentDto() {
    }


    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
