package com.codegym.case_study_team_2_study.controller.classes;

import com.codegym.case_study_team_2_study.dto.classes.ClassDto;
import com.codegym.case_study_team_2_study.dto.classes.ListClassesDto;
import com.codegym.case_study_team_2_study.dto.student.StudentDto;
import com.codegym.case_study_team_2_study.model.classes.Classes;
import com.codegym.case_study_team_2_study.service.classes.IClassesService;
import com.codegym.case_study_team_2_study.service.student.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private IClassesService iClassesService;
    @Autowired
    private IStudentService iStudentService;

    @GetMapping("")
    public String showClass(@RequestParam(defaultValue = "", required = false) String name,
                            Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Classes> classList= iClassesService.findClass(pageable, name);
        model.addAttribute("classList", classList);
        model.addAttribute("name", name);
        return "/classes/list";
    }
    @GetMapping("/listStudent")
    public String showStudent(
//            @RequestParam(defaultValue = "",required = false)String studentName,
            @RequestParam(defaultValue = "0",required = false) int page,
            @RequestParam(defaultValue = "-1", required = false) int id, Model model) {
        Pageable pageable = PageRequest.of(page,5);
        Page<StudentDto> studentDtoPage = iClassesService.findStudent(pageable,id);
        List <ListClassesDto> classesDto =  iClassesService.findAllClass(id);
        model.addAttribute("classesDto",classesDto);
        model.addAttribute("studentDtoPage", studentDtoPage);
        model.addAttribute("id", id);
        return "/classes/newDetail";
    }
    @ModelAttribute("classesList")
    public List<Classes> list() {
        return iClassesService.findAll();
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        ClassDto classDto = new ClassDto();
        model.addAttribute("classDto", classDto);
        return "/classes/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("classDto") ClassDto classDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
       new ClassDto().validate(classDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "/classes/add";
        }
        Classes classes = new Classes();
        classes.setStatus(true);
        BeanUtils.copyProperties(classDto, classes);
        iClassesService.add(classes);
        redirectAttributes.addFlashAttribute("mess", "thêm thành công");
        return "redirect:/classes";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Classes classes = iClassesService.findById(id);
        iClassesService.delete(classes);
        redirectAttributes.addFlashAttribute("mess", "xoá thành công");
        return "redirect:/classes";
    }

}
