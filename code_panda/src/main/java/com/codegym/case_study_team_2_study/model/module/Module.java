package com.codegym.case_study_team_2_study.model.module;

import com.codegym.case_study_team_2_study.model.document.Document;

import javax.persistence.*;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "module_name")
    private String name;
    @Column(name = "module_interval")
    private String interval;
    @OneToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;
    private Boolean status;

    public Module() {
    }

    public Module(int id, String name, String interval, Document document, Boolean status) {
        this.id = id;
        this.name = name;
        this.interval = interval;
        this.document = document;
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

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
