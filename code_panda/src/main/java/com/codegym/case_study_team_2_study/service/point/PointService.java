package com.codegym.case_study_team_2_study.service.point;

import com.codegym.case_study_team_2_study.dto.point.IPointDto;
import com.codegym.case_study_team_2_study.model.point.Point;
import com.codegym.case_study_team_2_study.repository.point.IPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PointService implements IPointService {
    @Autowired
    private IPointRepository pointRepository;
    @Override
    public Page<IPointDto> searchByAvg(Pageable pageable, String searchAvg, String classesName) {
        return pointRepository.findPointByAvg(pageable,"%"+searchAvg+"%", "%"+classesName+"%");
    }

    @Override
    public Point findById(int id) {
        return pointRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Point point) {
        pointRepository.delete(point);
    }

    @Override
    public void save(Point point) {
        pointRepository.save(point);
    }

    @Override
    public Point point(int id) {
        return pointRepository.findById(id).orElse(null);
    }

    @Override
    public void edit(int id, Point point) {
        pointRepository.save(point);
    }

    @Override
    public Point findByModuleIdAndStudentId(int id,int id2) {
        return pointRepository.findByName(id,id2);
    }

}
