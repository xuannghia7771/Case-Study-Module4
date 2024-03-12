package com.codegym.case_study_team_2_study.service.document;

import com.codegym.case_study_team_2_study.dto.document.DocumentDto;
import com.codegym.case_study_team_2_study.dto.document.IDocumentDto;
import com.codegym.case_study_team_2_study.model.document.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDocumentService {

//    Page<IDocumentDto> findAll(Pageable pageable, String searchName);

    Document findById(int id);

    void remove(Document document);

    void save(Document document);

    Document document(int id);

    void edit(int id, Document document);

    Page<Document> searchByName(Pageable pageable, String searchName);

    List<Document> displayDocument();
}
