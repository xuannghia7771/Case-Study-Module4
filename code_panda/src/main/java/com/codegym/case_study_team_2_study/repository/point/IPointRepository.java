package com.codegym.case_study_team_2_study.repository.point;

import com.codegym.case_study_team_2_study.dto.point.IPointDto;
import com.codegym.case_study_team_2_study.model.point.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IPointRepository extends JpaRepository<Point,Integer> {
    @Query(value = "select point.id as id, point.point_avg as pointAvg ,c.classes_name as classes, module.module_name as moduleName, student.student_name as studentName from point " +
            "join student on point.student_id = student.id \n" +
            "join module on point.module_id = module.id \n" +
            "join classes c on student.classes_id = c.id " +
            "where student.student_name like :searchAvg and c.classes_name like :classesName",nativeQuery = true)
    Page<IPointDto> findPointByAvg(Pageable pageable, @Param("searchAvg") String searchAvg, @Param("classesName") String classesName);


    @Query(value = "select * from point p" +
            " where p.module_id = :id and p.student_id = :id2 ",nativeQuery = true)
    Point findByName(@Param("id") int id, @Param("id2") int id2);
}
