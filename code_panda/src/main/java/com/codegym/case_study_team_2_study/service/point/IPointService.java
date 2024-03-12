package com.codegym.case_study_team_2_study.service.point;

import com.codegym.case_study_team_2_study.dto.point.IPointDto;
import com.codegym.case_study_team_2_study.model.point.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPointService {
    Page<IPointDto> searchByAvg(Pageable pageable, String searchAvg, String classesName);

    Point findById(int id);

    void remove(Point point);

    void save(Point point);

    Point point(int id);

    void edit(int id, Point point);

    Point findByModuleIdAndStudentId(int id1,int id2);
}
