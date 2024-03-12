package com.codegym.case_study_team_2_study.controller.teacher;

import com.codegym.case_study_team_2_study.dto.teacher.ITeacherDto;
import com.codegym.case_study_team_2_study.dto.teacher.TeacherDto;
import com.codegym.case_study_team_2_study.model.student.Student;
import com.codegym.case_study_team_2_study.model.teacher.Teacher;
import com.codegym.case_study_team_2_study.service.teacher.ITeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @ModelAttribute("teacherList")
    public List<Teacher> getListTeacher() {
        List<Teacher> teacherList = teacherService.findAll();
        return teacherList;
    }

    @GetMapping("")
    public String showList(Model model,
                           @RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "", required = false) String searchName) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<ITeacherDto> teacherDtoPage = teacherService.searchByName(pageable, searchName);
        model.addAttribute("teacherDtoPage", teacherDtoPage);
        model.addAttribute("searchName", searchName);
        return "/teacher/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("teacherDto", new TeacherDto());
        return "/teacher/create";
    }

    @PostMapping("/save")
    public String saveTeacher(@Valid @ModelAttribute TeacherDto teacherDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        new TeacherDto().validate(teacherDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/teacher/create";
        }
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDto, teacher);
        teacher.setStatus(true);
        teacherService.saveNewTeacher(teacher);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Teacher teacher = teacherService.findById(id);
        TeacherDto teacherDto = new TeacherDto();
        BeanUtils.copyProperties(teacher, teacherDto);
        model.addAttribute("teacherDto", teacherDto);
        return "/teacher/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute TeacherDto teacherDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        new TeacherDto().validate(teacherDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/teacher/create";
        }
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDto, teacher);
        teacher.setStatus(true);
        teacherService.updateTeacher(teacher.getId(), teacher);
        redirectAttributes.addFlashAttribute("message", "Sửa thành công");
        return "redirect:/teachers";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int idDelete, RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherService.findById(idDelete);
        teacherService.delete(teacher);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/teachers";
    }
}