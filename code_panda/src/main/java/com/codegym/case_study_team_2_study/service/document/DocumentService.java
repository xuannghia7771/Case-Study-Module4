package com.codegym.case_study_team_2_study.service.document;

import com.codegym.case_study_team_2_study.dto.document.DocumentDto;
import com.codegym.case_study_team_2_study.dto.document.IDocumentDto;
import com.codegym.case_study_team_2_study.model.document.Document;
import com.codegym.case_study_team_2_study.repository.document.IDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService implements IDocumentService {
    @Autowired
    private IDocumentRepository documentRepository;

//    @Override
//    public Page<IDocumentDto> findAll(Pageable pageable, String searchName) {
//        return documentRepository.findDocumentByNameContaining(pageable,"%"+searchName+"%");
//    }

    @Override
    public Document findById(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Document document) {
        documentRepository.deleteDocument(document);
    }

    @Override
    public void save(Document document) {
        documentRepository.save(document);
    }

    @Override
    public Document document(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public void edit(int id, Document document) {
        documentRepository.save(document);
    }

    @Override
    public Page<Document> searchByName(Pageable pageable, String searchName) {
        return documentRepository.findDocumentByNameContaining(pageable, "%"+searchName+"%");
    }

    @Override
    public List<Document> displayDocument() {
        return documentRepository.findAll();
    }
}
