package com.codegym.case_study_team_2_study.controller.position;

import com.codegym.case_study_team_2_study.model.position.Position;
import com.codegym.case_study_team_2_study.service.position.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PositionController {
    @Autowired
    private IPositionService positionService;
    @ModelAttribute("positions")
    public List<Position> getAll(){
       return positionService.getAll();
    }
}
