package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;


@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer dateOfBirth,
        @JsonAlias("death_year") Integer dateOfDeath
) {
    @Override
    public String toString() {
        return name + '\n' +
                "Año de nacimiento: " + dateOfBirth + "\n" +
                "Año de fallecimiento: " + dateOfDeath
                ;
    }
}


