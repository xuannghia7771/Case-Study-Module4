package com.codegym.case_study_team_2_study.model.point;

import com.codegym.case_study_team_2_study.model.module.Module;
import com.codegym.case_study_team_2_study.model.student.Student;

import javax.persistence.*;

@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @Column(name = "point_avg")
    private double pointAvg;

    public Point() {
    }

    public Point(int id, Module module, Student student, double pointAvg) {
        this.id = id;
        this.module = module;
        this.student = student;
        this.pointAvg = pointAvg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getPointAvg() {
        return pointAvg;
    }

    public void setPointAvg(double pointAvg) {
        this.pointAvg = pointAvg;
    }
}
