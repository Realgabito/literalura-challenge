package com.literalura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;


public record AuthorsDataDTO(
       String name,
       String DoB,
       String DoD
) {
    public AuthorsDataDTO(String name, String DoB, String DoD) {
        this.DoB = DoB;
        this.name = name;
        this.DoD = DoD;

    }
}
