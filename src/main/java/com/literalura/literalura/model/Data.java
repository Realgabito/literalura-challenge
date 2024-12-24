package com.literalura.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.print.Book;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(
        @JsonAlias("results")List<BooksData> books

        ) {
        @Override
        public String toString() {

                //Convertir la lista de libros en una cadena sin corchetes
                String booksString = books.stream()
                        .map(BooksData::toString)
                        .reduce("", (partial, element) -> partial + element);

                return  booksString;
        }
}
