package com.literalura.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.print.Book;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(
        @JsonAlias("results")List<BooksData> books

        ) {
}
