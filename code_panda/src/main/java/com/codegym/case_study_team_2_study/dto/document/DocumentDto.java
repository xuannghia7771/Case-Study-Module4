package com.codegym.case_study_team_2_study.dto.document;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;

public class DocumentDto implements Validator {
    private int id;
//    @NotEmpty(message = "Không Được Để Trống")
    private String name;
//    @NotEmpty(message = "Không Được Để Trống")
    private String description;
    private Boolean status;

    public DocumentDto() {
    }

    public DocumentDto(int id, String name, String description,Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    DocumentDto documentDto = (DocumentDto) target;
    if (documentDto.getName().equals("")){
        errors.rejectValue("name",null,"Không được để trống");
    } else if (!documentDto.getName().matches("^(([A-Z][a-z]+) )+([A-Z][a-z]+){1}$")) {
        errors.rejectValue("name",null,"Tên phải viết hoa chữ cái đầu và mỗi từ phải cách nhau bởi khoảng trắng !");
    }
    if (documentDto.getDescription().equals("")){
        errors.rejectValue("description",null,"Mô tả không để trống");
    }
    }
}
