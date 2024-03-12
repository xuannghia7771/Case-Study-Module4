package com.codegym.case_study_team_2_study.repository.document;

import com.codegym.case_study_team_2_study.dto.document.DocumentDto;
import com.codegym.case_study_team_2_study.dto.document.IDocumentDto;
import com.codegym.case_study_team_2_study.model.document.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IDocumentRepository extends JpaRepository<Document,Integer> {

    @Query(value = "select * from document where document.status = true and document.document_name like :searchName",nativeQuery = true)
    Page<Document> findDocumentByNameContaining(Pageable pageable,
                                                   @Param("searchName") String searchName);

    @Transactional
    @Modifying
    @Query(value = " update document set document.status = false where document.id = :id", nativeQuery = true)
    void deleteDocument(@Param("id") Document document);

//    @Transactional
//    @Modifying
//    @Query(value = "insert into document (document_name,document_description) values (:name,:desciption)",nativeQuery = true)
//    void saveDocument(@Param("name") String name,@Param("description") String description);
}
