package com.literalura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.literalura.literalura.model.AuthorData;
import jdk.jfr.DataAmount;

import java.util.List;


public record BooksDataDTO(
        String title,
         List<AuthorData> author,
         List<String> languages,
         Double downloads
) {

}
