package com.codegym.case_study_team_2_study.repository.assignment;

import com.codegym.case_study_team_2_study.dto.assignment.IAssignmentDto;
import com.codegym.case_study_team_2_study.model.assignment.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Query(value = "SELECT " +
            "    a.id," +
            "    a.date_end as end, " +
            "    a.date_start as start, " +
            "    c.classes_name as classes, " +
            "    t.teacher_name as teacher, " +
            "    p.position_name as position " +
            "FROM " +
            "    assignment a" +
            "        JOIN" +
            "    classes c ON a.classes_id = c.id " +
            "        JOIN" +
            "    teacher t ON a.teacher_id = t.id " +
            "        JOIN" +
            "    position p ON a.position_id = p.id " +
            "WHERE" +
            "    a.date_start > :dateStart and a.date_start < :dateEnd " +
            "        AND c.classes_name LIKE :className " +
            "        AND t.teacher_name LIKE :teacherName", nativeQuery = true)
    Page<IAssignmentDto> findAll(Pageable pageable,
                                 @Param("className") String className,
                                 @Param("teacherName") String teacherName,
                                 @Param("dateStart") String dateStart,
                                 @Param("dateEnd") String dateEnd);

    @Query(value = "select * from assignment where teacher_id=:id and classes_id=:id1 and position_id = :id2 ", nativeQuery = true)
    Assignment checkExist(@Param("id") int id,@Param("id1") int id1,@Param("id2") int id2);
}
