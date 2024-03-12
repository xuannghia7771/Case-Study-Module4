package com.codegym.case_study_team_2_study.controller.document;

import com.codegym.case_study_team_2_study.dto.document.DocumentDto;
import com.codegym.case_study_team_2_study.dto.document.IDocumentDto;
import com.codegym.case_study_team_2_study.model.document.Document;
import com.codegym.case_study_team_2_study.service.document.IDocumentService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    //    @GetMapping("")
//    public String showList(Model model, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "")String searchName){
//        Pageable pageable = PageRequest.of(page,2,Sort.by("document_name").ascending());
//        Page <Document> documentPage = documentService.findAll(pageable,searchName);
//        model.addAttribute("documentPage",documentPage);
//        model.addAttribute("searchName",searchName);
//        return "document/list";
//    }
    @GetMapping("")
    public ModelAndView showPage(@RequestParam(defaultValue = "0", required = false) int page,
                                 @RequestParam(defaultValue = "", required = false) String searchName) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("document_name").ascending());
        Page<Document> documentPageDto = documentService.searchByName(pageable, searchName);
        ModelAndView modelAndView = new ModelAndView("/document/list", "documentPageDto", documentPageDto);
        modelAndView.addObject("searchName", searchName);
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteDocument(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Document document = documentService.findById(id);
        documentService.remove(document);
        redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        return "redirect:/document";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("documentDto", new DocumentDto());
        return "/document/create";
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute DocumentDto documentDto,
                         RedirectAttributes redirectAttributes,
                         BindingResult bindingResult) {
        new DocumentDto().validate(documentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/document/create";
        }
        Document document = new Document();
        BeanUtils.copyProperties(documentDto, document);
        document.setStatus(true);
        documentService.save(document);
        redirectAttributes.addFlashAttribute("msg", "Thêm Mới Thành Công");
        return "redirect:/document";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model,DocumentDto documentDto) {
        Document document = documentService.document(id);
        BeanUtils.copyProperties(document,documentDto);
        model.addAttribute("documentDto", documentDto);
        return "/document/update";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute DocumentDto documentDto,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult) {
        new DocumentDto().validate(documentDto,bindingResult);
        if (bindingResult.hasErrors()){
            return "/document/update";
        }
        Document document = new Document();
        BeanUtils.copyProperties(documentDto,document);
        document.setStatus(true);
        documentService.edit(document.getId(), document);
        redirectAttributes.addFlashAttribute("msg", "Chỉnh Sửa Thành Công");
        return "redirect:/document";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable int id, Model model) {
        Document document = documentService.findById(id);
        model.addAttribute("document", document);
        return "document/detail";
    }
}
