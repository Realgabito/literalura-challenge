package com.literalura.literalura.main;

import com.literalura.literalura.model.*;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BooksRepository;
import com.literalura.literalura.service.ConvertData;
import com.literalura.literalura.service.RequestAPI;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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
    private AuthorRepository authorRepository;

    public Main(BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
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


                case 2:
                    listRegisteredBooks();
                    break;

                case 3:
                    listRegisteredAuthors();
                    break;

                case 4:
                    listAuthorsAlive();
                    break;

                case 5:
                    listBooksByLanguages();
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

        for (AuthorData authorData : books.author()) {
            Author author = authorRepository.findByNameIgnoreCase(authorData.name())
                    .orElseGet(() -> {
                        Author newAuthor = new Author();
                        newAuthor.setName(authorData.name());
                        newAuthor.setDateOfBirth(authorData.dateOfBirth());
                        newAuthor.setDateOfBirth(authorData.dateOfDeath());
                        authorRepository.save(newAuthor);
                        return newAuthor;
                    });


            Optional<Books> existingBook = booksRepository.findByTitleContainingIgnoreCase(books.title());

            if (existingBook.isPresent()) {
                System.out.println("""
                      \n-------------------------------------------------------------\n
                      \n--------     EL LIBRO YA ESTA REGISTRADO    --------\n
                      \n-------------------------------------------------------------\n
                      """ + existingBook.get());
            } else {

                Books book = new Books();
                book.setTitle(books.title());
                book.setLanguages(language);
                book.setDownloads(downloads);
                book.setAuthor(author);


                booksRepository.save(book);
                System.out.println(book + """
                        
                        \n-------------------------------------------------------------\n
                        \n--------      LIBRO REGISTRADO    --------\n
                        \n-------------------------------------------------------------\n
                        """);
            }


//           try {
//               booksRepository.save(book);
//           } catch (DataIntegrityViolationException e) {
//               System.out.println("""
//                       \n-------------------------------------------------------------\n
//                       \n--------     EL LIBRO YA ESTA REGISTRADO    --------\n
//                       \n-------------------------------------------------------------\n
//                       """);
//           }

//            System.out.println( books);

        }
    }


    private BooksData getBookData() {
        try {
            System.out.println("""
                      \n-------------------------------------------------------------\n
                      \n--------     INGRESE EL NOMBRE DEL LIBRO    --------\n
                      \n-------------------------------------------------------------\n
                      """ );
            var bookTitle = userInput.nextLine();

            if (bookTitle.isEmpty()) {
                System.out.println("""
                      \n-------------------------------------------------------------\n
                      \n--------     EL TITULO NO PUEDE ESTAR VACIO    --------\n
                      \n-------------------------------------------------------------\n
                      """ );
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

    private void listRegisteredBooks() {
        books = booksRepository.findAll();

        books.stream()
                .sorted(Comparator.comparing(Books::getLanguages))
                .forEach(System.out::println);
    }

    private void listRegisteredAuthors() {
        authors = authorRepository.findAll();
        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(System.out::println);

    }

    private void listAuthorsAlive() {
        System.out.println("""
                      \n-------------------------------------------------------------\n
                      \n--------     INGRESE EL AÑO QUE DESEA CONSULTAR    --------\n
                      \n-------------------------------------------------------------\n
                      """ );
        var year = userInput.nextInt();
        userInput.nextLine();
        authors = authorRepository.findAliveInYear(year);

        if (authors.isEmpty()) {
            System.out.println("No pudimos encontrar autores vivos en el ano: " + year);
        } else {
            authors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(System.out::println);
        }
    }

    private void listBooksByLanguages() {
        var language = showLanguages();
        books = booksRepository.findByLanguagesContainingIgnoreCase(language);

        if (books.isEmpty()) {
            System.out.println("""
                      \n-------------------------------------------------------------\n
                      \n--------     NO SE ENCONTRARON LIBROS CON ESE IDIOMA EN LA BASE DE DATOS   --------\n
                      \n-------------------------------------------------------------\n
                      """ );
        } else {
            books.stream()
                    .sorted(Comparator.comparing(Books::getTitle))
                    .forEach(System.out::println);
        }
    }

    private String showLanguages() {
        var language = "";
        var menu = """
                \n-------------------------------------------------------------\n
                \n--------     INGRESE EL IDIOMA QUE DESEA BUSCAR     --------\n
                \n-------------------------------------------------------------\n
                
                1 - [es] ---> Español
                2 - [en] ---> Ingles
                3 - [fr] ---> Frances
                4 - [pr] ---> Portugues
                
                
                -------------------------------------------------------------
                """;

        System.out.println(menu);
        return userInput.nextLine().replace(" ", "").toLowerCase();
    }


}
