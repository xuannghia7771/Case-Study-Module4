package com.codegym.case_study_team_2_study.service.assignment;

import com.codegym.case_study_team_2_study.dto.assignment.IAssignmentDto;
import com.codegym.case_study_team_2_study.model.assignment.Assignment;
import com.codegym.case_study_team_2_study.repository.assignment.IAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService implements IAssignmentService {
    @Autowired
    private IAssignmentRepository assignmentRepository;
    @Override
    public Page<IAssignmentDto> getAll(Pageable pageable, String className, String teacherName, String dateStart, String dateEnd) {
        return assignmentRepository.findAll(pageable,"%"+className+"%","%"+teacherName+"%",dateStart,dateEnd);
    }

    @Override
    public void add(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    @Override
    public Assignment findById(int id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    @Override
    public Assignment checkExist(int id, int id1, int id2) {
        return assignmentRepository.checkExist(id,id1,id2);
    }

    @Override
    public void remove(Assignment assignment) {
        assignmentRepository.delete(assignment);
    }
}