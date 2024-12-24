package com.literalura.literalura.main;

import com.literalura.literalura.model.Data;
import com.literalura.literalura.service.ConvertData;
import com.literalura.literalura.service.RequestAPI;
import java.util.Scanner;

public class Main {
    private RequestAPI requestAPI = new RequestAPI();
    private ConvertData converter = new ConvertData();
    private final String URL = "https://gutendex.com/books/";
    private Scanner userInput = new Scanner(System.in);


    public void showMenu() {
        var json = requestAPI.getData(URL);
//        System.out.println(json);

        var data = converter.getData(json, Data.class);
        System.out.println(data);

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


        }
    }
}
