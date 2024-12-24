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

        String authorString = author.stream()
                .map(AuthorData::toString)
                .reduce("", (partial, element) -> partial + element);

        String languagesString = String.join(", ", languages);

        return  "--------LIBRO--------" + "\n" +
                "Titulo: " + title + '\n' +
                "Autor: " + authorString + '\n' +
                "Idiomas: " + languagesString + '\n' +
                "Numero de Descargas: " + downloads + '\n' +
                        "-------------------\n" + "\n"
                ;
    }
}
