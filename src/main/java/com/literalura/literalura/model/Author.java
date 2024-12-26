package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private Integer dateOfBirth;
    private Integer dateOfDeath;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Books> books;

    public Author() {}

    public Author(String name, Integer dateOfBirth, Integer dateOfDeath) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Integer dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return  "\n---------------------------------------------\n" +
                "---------      AUTORES     -----------\n" +
                "---------------------------------------------\n" +
                "Nombre: " + name + '\n' +
                "Año de nacimiento: " + dateOfBirth + '\n' +
                "Año de fallecimiento: " + dateOfDeath + '\n' +
                "\n---------------------------------------------\n";
    }
}
