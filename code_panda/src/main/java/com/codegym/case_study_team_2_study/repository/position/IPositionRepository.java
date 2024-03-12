package com.codegym.case_study_team_2_study.repository.position;

import com.codegym.case_study_team_2_study.model.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position,Integer> {

}
