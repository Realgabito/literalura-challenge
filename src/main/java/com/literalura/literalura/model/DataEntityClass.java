package com.literalura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "literalura")
public class DataEntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title")
    private String title;

    @Column(name = "authors")
    private String author;

    @Column(name = "langugages")
    private String languages;

    @Column(name = "downloads")
    private Double downloads;

    @Column(name = "author-name")
    private String name;

    @Column(name = "date-of-birth")
    private String DoB;

    @Column(name = "date-of-death")
    private String DoD;

    public DataEntityClass(){

    }

    public DataEntityClass(String title, String author, String languages, Double downloads, String name, String doB, String doD) {
        this.title = title;
        this.author = author;
        this.languages = languages;
        this.downloads = downloads;
        this.name = name;
        this.DoB = doB;
        this.DoD = doD;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getDoD() {
        return DoD;
    }

    public void setDoD(String doD) {
        DoD = doD;
    }
}
