package com.codegym.case_study_team_2_study.service.position;

import com.codegym.case_study_team_2_study.model.position.Position;
import com.codegym.case_study_team_2_study.repository.position.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }
}
