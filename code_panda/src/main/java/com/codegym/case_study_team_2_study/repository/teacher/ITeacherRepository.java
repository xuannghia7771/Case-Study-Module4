package com.codegym.case_study_team_2_study.repository.teacher;

import com.codegym.case_study_team_2_study.dto.teacher.ITeacherDto;
import com.codegym.case_study_team_2_study.model.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;


public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "select id, " +
            "teacher_name as name, " +
            "teacher_gender as gender, " +
            "teacher_birth as birth, " +
            "teacher_salary as salary, " +
            "teacher_phone as phone, " +
            "teacher_address as address " +
            "from teacher where teacher_status = true and teacher_name like :name", nativeQuery = true)
    Page<ITeacherDto> findTeacherByNameContaining(Pageable pageable, @Param("name") String searchName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teacher (teacher_address," +
            "teacher_birth, " +
            "teacher_gender, " +
            "teacher_name, " +
            "teacher_phone, " +
            "teacher_salary, " +
            "teacher_status) " +
            "VALUES (:address, :birth, :gender, :name, :phone, :salary, true)", nativeQuery = true)
    void saveNewTeacher(@Param("name") String name, @Param("gender") boolean gender, @Param("birth") LocalDate birth, @Param("salary") double salary, @Param("phone") String phone, @Param("address") String address);

    @Modifying
    @Transactional
    @Query(value = "update teacher set teacher_status = false where id = :id", nativeQuery = true)
    void deleteTeacher(@Param("id") Teacher teacher);

//    @Modifying
//    @Transactional
//    @Query(value = "update teacher set teacher_address = :address, teacher_birth = :birth, teacher_gender = :gender, teacher_name = :name, teacher_phone = :phone, teacher_salary = :salary where id = :id", nativeQuery = true)
//    void updateTeacher(@Param("id") int id, @Param("name") String name, @Param("gender") boolean gender, @Param("birth") LocalDate birth, @Param("salary") double salary, @Param("phone") String phone, @Param("address") String address);
}
