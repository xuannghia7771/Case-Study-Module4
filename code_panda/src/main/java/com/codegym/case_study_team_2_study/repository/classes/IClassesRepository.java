package com.codegym.case_study_team_2_study.repository.classes;

import com.codegym.case_study_team_2_study.dto.classes.ListClassesDto;
import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClassesRepository extends JpaRepository<Classes, Integer> {
//    @Query(value = "SELECT cla.id AS classId, cla.classes_name AS className, mo.module_name AS moduleName, tea.teacher_name AS teacherName, pos.position_name AS positionName, COUNT(st.id) as studentCount " +
//            "FROM classes cla " +
//            "LEFT JOIN student st ON st.classes_id = cla.id " +
//            "LEFT JOIN point p ON p.student_id = st.id " +
//            "LEFT JOIN module mo ON mo.id = p.module_id " +
//            "LEFT JOIN assignment ass ON ass.classes_id = cla.id " +
//            "LEFT JOIN teacher tea ON tea.id = ass.teacher_id " +
//            "LEFT JOIN position pos ON pos.id = ass.position_id " +
//            "WHERE cla.id = :classId and cla.status = true " +
//            "group by cla.id ",  nativeQuery = true)
//    List<ListClassesDto> findAllClass(@Param(value = "classId") int classId);

    @Query(value = "SELECT cla.id AS classId, cla.classes_name AS className,MAX( mo.module_name) AS moduleName, tea.teacher_name AS teacherName, pos.position_name AS positionName, COUNT(st.id) AS studentCount " +
            "FROM classes cla " +
            "LEFT JOIN student st ON st.classes_id = cla.id " +
            "LEFT JOIN point p ON p.student_id = st.id " +
            "LEFT JOIN module mo ON mo.id = p.module_id " +
            "LEFT JOIN assignment ass ON ass.classes_id = cla.id " +
            "LEFT JOIN teacher tea ON tea.id = ass.teacher_id " +
            "LEFT JOIN position pos ON pos.id = ass.position_id " +
            "WHERE cla.id = :classId and cla.status = true " +
            "GROUP BY cla.id, cla.classes_name, tea.teacher_name, pos.position_name ", nativeQuery = true)
    List<ListClassesDto> findAllClass(@Param(value = "classId") int classId);

    @Modifying
    @Transactional
    @Query(value = "update classes set status = false where id=:id", nativeQuery = true)
    void deleteClass(@Param(value = "id") Classes classes);
    @Query(value = "select * from classes where status=true and classes_name like :name",nativeQuery = true)
    Page<Classes> listClass(Pageable pageable,@Param(value = "name") String name);

    @Query(value = "SELECT " +
            "    cla.id as classId, " +
            "    cla.classes_name as className, " +
            "    st.id as studentId, " +
            "    st.student_birth as studentBirth, " +
            "    st.student_gender as studentGender, " +
            "    st.id_card as studentIdCard, " +
            "    st.student_name as studentName, " +
            "    st.student_phone as studentPhone " +
            "   FROM " +
            "      student st " +
            "   left JOIN classes cla ON st.classes_id = cla.id " +
            "    where cla.id = :classId and cla.status = true " +
            "     ", nativeQuery = true)
    Page<StudentDto> listStudent(Pageable pageable, @Param("classId") int classId);


}
