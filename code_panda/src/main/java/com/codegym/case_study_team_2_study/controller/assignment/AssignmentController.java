package com.codegym.case_study_team_2_study.controller.assignment;

import com.codegym.case_study_team_2_study.dto.assignment.AssignmentDto;
import com.codegym.case_study_team_2_study.dto.assignment.IAssignmentDto;
import com.codegym.case_study_team_2_study.model.assignment.Assignment;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import com.codegym.case_study_team_2_study.model.position.Position;
import com.codegym.case_study_team_2_study.model.teacher.Teacher;
import com.codegym.case_study_team_2_study.service.assignment.IAssignmentService;
import com.codegym.case_study_team_2_study.service.classes.IClassesService;
import com.codegym.case_study_team_2_study.service.position.IPositionService;
import com.codegym.case_study_team_2_study.service.teacher.ITeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
    @ModelAttribute("assignmentDto")
    public AssignmentDto newAssignmentDto(){
        return new AssignmentDto();
    }
    @Autowired
    private IAssignmentService assignmentService;
    @Autowired
    private ITeacherService teacherService;
     @Autowired
    private IClassesService classesService;
     @Autowired
    private IPositionService positionService;
    @ModelAttribute("teacherLists")
    public List<Teacher> showListTeacher(){
        return teacherService.findAll();
    }
    @ModelAttribute("classesLists")
    public List<Classes> showListClasses(){
        return classesService.findAll();
    }
    @ModelAttribute("positionLists")
    public List<Position> showListPosition(){
        return positionService.getAll();
    }

    @GetMapping("/list")
    public String showAssignment(@RequestParam(defaultValue = "0", required = false) int page,
                                 @RequestParam(defaultValue = "", required = false) String className,
                                 @RequestParam(defaultValue = "", required = false) String teacherName,
                                 @RequestParam(defaultValue = "2018-01-01", required = false) String start,
                                 @RequestParam(defaultValue = "", required = false) String end,
                                 @ModelAttribute AssignmentDto assignmentDto,
                                 Model model) {
        if (Objects.equals(end, "")) {
            end = String.valueOf(LocalDate.now());
        }
        Pageable pageable = PageRequest.of(page, 5, Sort.by("classes").ascending());
        Page<IAssignmentDto> assignmentPage = assignmentService.getAll(pageable, className, teacherName, start, end);
        model.addAttribute("assignmentPage", assignmentPage);
        model.addAttribute("page", page);
        model.addAttribute("className", className);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("assignmentDto",assignmentDto);
        return "/assignment/list";
    }
    @GetMapping("/create")
    public String showFormCreate(@ModelAttribute AssignmentDto assignmentDto,
                                 RedirectAttributes redirectAttributes) {
        if(assignmentDto.getDateStart().isAfter(assignmentDto.getDateEnd())){
            redirectAttributes.addFlashAttribute("msg","ngày bắt đầu không dược sau ngày kết thúc");
          redirectAttributes.addFlashAttribute("assignmentDto",assignmentDto);
          return "redirect:/assignment/list";
        }
        AssignmentDto assignmentDto1=new AssignmentDto();
        Assignment assignment=assignmentService.checkExist(assignmentDto.getTeacher().getId(),assignmentDto.getClasses().getId(),assignmentDto.getPosition().getId());
        if (assignment!=null){
            BeanUtils.copyProperties(assignment,assignmentDto1);
            if (assignmentDto.getDateEnd().isBefore(assignmentDto1.getDateEnd())&&assignmentDto.getDateEnd().isAfter(assignmentDto1.getDateStart())){
                redirectAttributes.addFlashAttribute("msg","bảng này đã bị chồng khoảng thời gian");
                redirectAttributes.addFlashAttribute("assignmentDto",assignmentDto);
                return "redirect:/assignment/list";
            }
            if (assignmentDto.getDateStart().isBefore(assignmentDto1.getDateEnd())&&assignmentDto.getDateStart().isAfter(assignmentDto.getDateStart())){
                redirectAttributes.addFlashAttribute("msg","bảng này đã bị chồng khoảng thời gian");
                redirectAttributes.addFlashAttribute("assignmentDto",assignmentDto);
                return "redirect:/assignment/list";
            }
            if (assignmentDto.getDateStart().isBefore(assignmentDto1.getDateStart())&&assignmentDto.getDateStart().isAfter(assignmentDto.getDateEnd())){
                redirectAttributes.addFlashAttribute("msg","bảng này đã bị chồng khoảng thời gian");
                redirectAttributes.addFlashAttribute("assignmentDto",assignmentDto);
                return "redirect:/assignment/list";
            }
            if (assignmentDto.getDateStart().isAfter(assignmentDto1.getDateEnd())||assignmentDto.getDateEnd().isBefore(assignmentDto1.getDateStart())){
                Assignment assignment1=new Assignment();
                BeanUtils.copyProperties(assignmentDto,assignment1);
                assignmentService.add(assignment1);
                redirectAttributes.addFlashAttribute("msg","Đã thêm mới lịch phân công của giảng viên :"+assignment1.getTeacher().getName());
                return "redirect:/assignment/list";
            }
            redirectAttributes.addFlashAttribute("msg","bảng phân  công đã tồn tại");
            redirectAttributes.addFlashAttribute("assignmentDto",assignmentDto);
            return "redirect:/assignment/list";
        }
        Assignment assignment1=new Assignment();
        BeanUtils.copyProperties(assignmentDto,assignment1);
        assignmentService.add(assignment1);
        redirectAttributes.addFlashAttribute("msg","Đã thêm mới lịch phân công của giảng viên :"+assignment1.getTeacher().getName());
        return "redirect:/assignment/list";
    }
    @GetMapping("/update")
    public String showFormEdit(@RequestParam int id, Model model) {
        Assignment assignment=assignmentService.findById(id);
        System.out.println(assignment.getDateStart());
        AssignmentDto assignmentDto=new AssignmentDto();
        BeanUtils.copyProperties(assignment,assignmentDto);
        System.out.println(assignmentDto.getDateStart());
        model.addAttribute("assignmentDto",assignmentDto);
        return "/assignment/edit";
    }
    @PostMapping("/edit")
    public String editAssignment(@ModelAttribute AssignmentDto assignmentDto,
                                 RedirectAttributes redirectAttributes) {
        Assignment assignment= assignmentService.findById(assignmentDto.getId());
        if (assignment!=null){
            BeanUtils.copyProperties(assignmentDto,assignment);
            assignmentService.add(assignment);
        }
        redirectAttributes.addFlashAttribute("msg","Sửa bảng phân công của giáo viên "+assignmentDto.getTeacher().getName()+" thành công");
        return "redirect:/assignment/list";
    }
    @GetMapping("/delete")
    public String deleteAssignment(@RequestParam int idDelete,
                                 RedirectAttributes redirectAttributes) {
         Assignment assignment= assignmentService.findById(idDelete);
         assignmentService.remove(assignment);
        redirectAttributes.addFlashAttribute("msg","xóa thành công");
        return "redirect:/assignment/list";
    }
}
