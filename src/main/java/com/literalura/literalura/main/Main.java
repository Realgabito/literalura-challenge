package com.literalura.literalura.main;

import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Books;
import com.literalura.literalura.model.BooksData;
import com.literalura.literalura.model.Data;
import com.literalura.literalura.repository.BooksRepository;
import com.literalura.literalura.service.ConvertData;
import com.literalura.literalura.service.RequestAPI;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main {

    private RequestAPI requestAPI = new RequestAPI();
    private ConvertData converter = new ConvertData();
    private final String URL = "https://gutendex.com/books/";
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner userInput = new Scanner(System.in);

    private List<BooksData> booksData;
    private List<Books> books;
    private List<Author> authors;
    private Optional<Books> searchedBook;
    private BooksRepository booksRepository;

    public Main(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public void showData() {
        var json = requestAPI.getData(URL);
//        System.out.println(json);

        var data = converter.getData(json, Data.class);
        System.out.println(data);

    }


    public void showMenu() {
        var option = -1;
        while  (option != 0) {
            var menu = """
                    Elija la opcion a traves de su numero:
                    1 - Buscar libro por titulo
                    2 - Listar todos los libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;

            System.out.println(menu);
            option = userInput.nextInt();
            userInput.nextLine();

            switch (option) {
                case 1:
                    searchBookWeb();
                    break;
            }


        }
    }

    private void searchBookWeb() {
        BooksData books = getBookData();
        if (books == null) {
            System.out.println("Regresando al menu principal");
            return;
        } else {
            System.out.println("Libro Encontrado!: " + books);
        }

        String language = books.languages().isEmpty() ? "Idioma no encontrado" : books.languages().get(0);
        Double downloads = books.downloads();
    }


    private BooksData getBookData() {
        try {
            System.out.println("Escribe el nombre del libro que deseas buscar");
            var bookTitle = userInput.nextLine();

            if (bookTitle.isEmpty()) {
                System.out.println("El titulo del libro no puede estar vacio");
                return null;
            }

            var url = URL_BASE + bookTitle.replace(" ", "%20");
            var json = requestAPI.getData(url);

            if (json == null || json.isEmpty()) {
                System.out.println("No se puede obtener una respuesta valida de la API");
                return null;
            }

            var searchData = converter.getData(json, Data.class);


            if (searchData == null || searchData.books() == null || searchData.books().isEmpty()) {
                System.out.println("No se encontraron datos para el libro buscado. Regresando al menu principal...");
                return null;
            }

            Optional<BooksData> searchedBook = searchData.books().stream()
                    .filter(b -> b.title() != null && b.title().toUpperCase().contains(bookTitle.toUpperCase()))
                    .findFirst();



            return searchedBook.orElse(null);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar el libro" + e);
            return null;
        }
    }


}
