package com.codegym.case_study_team_2_study.repository.student;

import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import com.codegym.case_study_team_2_study.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "SELECT " +
            "    cla.classes_name as className, " +
            "    st.id as studentId," +
            "    st.student_birth as studentBirth, " +
            "    st.student_gender as  studentGender, " +
            "    st.id_card as studentIdCard, " +
            "    st.student_name as studentName, " +
            "    st.student_phone as studentPhone," +
            "    st.status as studentStatus " +
            "   FROM " +
            "      student st " +
            "   left JOIN classes cla ON st.classes_id = cla.id " +
            "    where st.student_name like :studentName and st.status = true ", nativeQuery = true)
    Page<StudentDto> findAllStudent(Pageable pageable, @Param("studentName") String studentName);


    @Modifying
    @Transactional
    @Query(value = " update student set status = false where id=:id", nativeQuery = true)
    void deleteId(@Param(value = "id") Student student);



}
