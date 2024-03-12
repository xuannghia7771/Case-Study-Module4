package com.codegym.case_study_team_2_study.controller.point;

import com.codegym.case_study_team_2_study.dto.point.IPointDto;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import com.codegym.case_study_team_2_study.model.module.Module;
import com.codegym.case_study_team_2_study.model.point.Point;
import com.codegym.case_study_team_2_study.model.student.Student;
import com.codegym.case_study_team_2_study.service.classes.IClassesService;
import com.codegym.case_study_team_2_study.service.module.IModuleService;
import com.codegym.case_study_team_2_study.service.point.IPointService;
import com.codegym.case_study_team_2_study.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/point")
public class PointController {
    @Autowired
    private IPointService pointService;
    @Autowired
    private IModuleService moduleService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassesService classesService;
    @ModelAttribute("listClass")
    public List<Classes> showList(){
        return classesService.findAll();
    }


    @GetMapping("")
    public ModelAndView showPage(@RequestParam(defaultValue = "0", required = false) int page,
                                 @RequestParam(defaultValue = "", required = false) String searchAvg,
                                 @RequestParam(defaultValue = "",required = false) String classesName) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<IPointDto> pointDtoPage = pointService.searchByAvg(pageable, searchAvg,classesName);
        ModelAndView modelAndView = new ModelAndView("point/list", "pointDtoPage", pointDtoPage);
        modelAndView.addObject("searchAvg", searchAvg);
        modelAndView.addObject("classesName",classesName);
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deletePoint(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Point point = pointService.findById(id);
        pointService.remove(point);
        redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        return "redirect:/point";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        List<Module> moduleList = moduleService.displayModule();
        List<Student> studentList = studentService.findAll();
        model.addAttribute("moduleList", moduleList);
        model.addAttribute("studentList", studentList);
        model.addAttribute("point", new Point());
        return "point/create";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Point point, RedirectAttributes redirectAttributes, Model model) {
        if (pointService.findByModuleIdAndStudentId(point.getModule().getId(),point.getStudent().getId())==null){
            pointService.save(point);
            redirectAttributes.addFlashAttribute("msg", "Thêm Mới Thành Công");
            return "redirect:/point";
        }
        model.addAttribute("point",point);
        model.addAttribute("msg","Bảng Này Đã Tồn Tại");
        return "/point/create";

    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Point point = pointService.point(id);
        model.addAttribute("point", point);
        return "point/update";

    }
    @PostMapping("/edit")
    public String edit (@ModelAttribute Point point, RedirectAttributes redirectAttributes){
        pointService.edit(point.getId(),point);
        redirectAttributes.addFlashAttribute("msg","Cập Nhật Thành Công");
        return "redirect:/point";
    }

}
