package com.codegym.case_study_team_2_study.controller.module;

import com.codegym.case_study_team_2_study.model.document.Document;
import com.codegym.case_study_team_2_study.model.module.Module;
import com.codegym.case_study_team_2_study.service.document.IDocumentService;
import com.codegym.case_study_team_2_study.service.module.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private IModuleService moduleService;
    @Autowired
    private IDocumentService documentService;
    @GetMapping("")
    public ModelAndView showPage(@RequestParam(defaultValue = "0",required = false) int page,
                                 @RequestParam(defaultValue = "",required = false)String searchName){
        Pageable pageable = PageRequest.of(page,5,Sort.by("module_name").ascending());
        Page<Module> modulePage = moduleService.searchByName(pageable,searchName);
        ModelAndView modelAndView = new ModelAndView("module/list","modulePage",modulePage);
        modelAndView.addObject("searchName",searchName);
        return modelAndView;
    }
    @GetMapping("/delete")
    public String deleteModule(@RequestParam int id, RedirectAttributes redirectAttributes){
        Module module = moduleService.findById(id);
        moduleService.remove(module);
        redirectAttributes.addFlashAttribute("msg","Xóa Thành Công");
        return "redirect:/module";
    }

    @GetMapping("/create")
    public String showCreate(Model model){
        List<Document> documentList = documentService.displayDocument();
        model.addAttribute("mo",new Module());
        model.addAttribute("documentList",documentList);
        return "/module/create";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Module module, RedirectAttributes redirectAttributes){
        module.setStatus(true);
        moduleService.save(module);
        redirectAttributes.addFlashAttribute("msg","Thêm Mới Thành Công");
        return "redirect:/module";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id,Model model){
        Module module = moduleService.module(id);
        List<Document> documentList = documentService.displayDocument();
        model.addAttribute("mo",module);
        model.addAttribute("documentList",documentList);
        return "/module/update";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Module module, RedirectAttributes redirectAttributes){
        module.setStatus(true);
        moduleService.edit(module.getId(),module);
        redirectAttributes.addFlashAttribute("msg","Chỉnh Sửa Thành Công");
        return "redirect:/module";
    }
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable int id, Model model){
        Module module = moduleService.findById(id);
        model.addAttribute("mo",module);
        return "/module/detail";
    }

}
