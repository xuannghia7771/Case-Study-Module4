package com.codegym.case_study_team_2_study.dto.classes;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ClassDto implements Validator {
    @NotEmpty(message = "không được để trống")
    private String name;
    boolean  status = true;
    private List<String> existingClassNames;

    public ClassDto() {
    }

    public ClassDto(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public ClassDto(String name, boolean status, List<String> existingClassNames) {
        this.name = name;
        this.status = status;
        this.existingClassNames = existingClassNames;
    }

    public List<String> getExistingClassNames() {
        return existingClassNames;
    }

    public void setExistingClassNames(List<String> existingClassNames) {
        this.existingClassNames = existingClassNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean supports(Class<?> clazz) {
         return ClassDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClassDto classDto = (ClassDto) target;
        String name = classDto.getName();
        List<String> existingClassNames = classDto.getExistingClassNames();
          if (!name.startsWith("C0")) {
            errors.rejectValue("name", "invalid", "Tên lớp phải bắt đầu bằng C0");
        }else if (existingClassNames != null && existingClassNames.contains(name)) {
              errors.rejectValue("name", "duplicate", "Tên lớp đã tồn tại");
          }

    }



}
