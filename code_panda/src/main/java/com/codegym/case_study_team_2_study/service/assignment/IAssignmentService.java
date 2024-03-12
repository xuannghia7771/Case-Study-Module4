package com.codegym.case_study_team_2_study.service.assignment;

import com.codegym.case_study_team_2_study.dto.assignment.IAssignmentDto;
import com.codegym.case_study_team_2_study.model.assignment.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAssignmentService {
    Page<IAssignmentDto> getAll(Pageable pageable, String className, String teacherName, String dateStart, String dateEnd);

    void add(Assignment assignment);

    Assignment findById(int id);

    Assignment checkExist(int id, int id1, int id2);

    void remove(Assignment assignment);
}
