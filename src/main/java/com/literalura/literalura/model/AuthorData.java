package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") String DoB,
        @JsonAlias("death_year") String DoD
) {
    @Override
    public String toString() {
        return  "Nombre: " + name + '\n' +
                "Año de nacimiento: " + DoB + '\n' +
                "Año de fallecimiento: " + DoD + '\n';
    }
}
