package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorData> author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double downloads
) {
    @Override
    public String toString() {
        return
                "Titulo: " + title + '\n' +
                "Autor: " + author + '\n' +
                "Idiomas: " + languages + '\n' +
                "Numero de Descargas: " + downloads;
    }
}
