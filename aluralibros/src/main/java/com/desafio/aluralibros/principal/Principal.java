package com.desafio.aluralibros.principal;

import com.desafio.aluralibros.model.*;
import com.desafio.aluralibros.repository.LibroRepository;
import com.desafio.aluralibros.service.ConsumoAPI;
import com.desafio.aluralibros.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private static final String URL_BASE ="https://gutendex.com/books/";
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoapi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private List<DatosLibros> datoslibro = new ArrayList<>();
    private List<Libros> libro;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }


    public void muestraElMenu() {
        var opcion = 1;
        while (opcion!= 0) {
            var menu = """
                    1 - Buscar libros por titulo
                    2 - Mostrar libros buscados
                    3 - Buscar libros por autor
                    4 - Mostrar lista de autores vivos en determinado año
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                case 1:
                    BuscarLibros();
                    break;
                case 2:
                    MostrarLibrosBuscados();
                    break;
                case 3:
                    BuscarLibrosPorAutor();
                    break;
                case 4:
                    MostrarAutoresVivos();
                    break;
                default:
                    System.out.println("Opcion inválida...");
            }
        }
    }
    private DatosLibros getDatosLibros(){
        System.out.print("Introduce el título del libro: ");
        var titulo = teclado.nextLine();
        var json = consumoapi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;
    }

    private void MostrarAutoresVivos() {
        System.out.println("Ingrese el año que deseas buscar al autor vivo:");
        var fecha = teclado.nextInt();
        List<Autor> autoresVivos = repositorio.buscarAutoresVivos(fecha);
        autoresVivos.forEach(System.out::println);
    }

    private void BuscarLibrosPorAutor() {
         System.out.println("Ingrese nombre del autor del libro a buscar: ");
         var autorIngresado = teclado.nextLine();
         var json = consumoapi.obtenerDatos(URL_BASE + "?search=" + autorIngresado.replace(" ", "+"));
         DatosLibros datosAutor = conversor.obtenerDatos(json,DatosLibros.class);
        Optional<DatosAutor> autorEncontrado = datosAutor.autor().stream().findFirst();
        if (autorEncontrado.isPresent()){
            DatosAutor datosautor = autorEncontrado.get();
            List<Autor> autores = autorEncontrado.stream()
                    .map(d -> new Autor(d.nombre(),d.fechaNacimiento(),d.fechaDeMuerte()))
                    .collect(Collectors.toList());
            Libros libro = new Libros();
            libro.setTitulo(datosAutor.titulo());
            libro.setAutor(datosAutor.autor());
            libro.setIdioma(datosAutor.idioma());
            libro.setNumeroDeDescargas(datosAutor.numeroDeDescargas());
            System.out.println("Libro encontrado: "+ libro);
            repositorio.save(libro);
         } else {
             System.out.println("Autor no encontrado...");
        }
    }

    private void MostrarLibrosBuscados() {
        libro = repositorio.findAll();
        libro.forEach(System.out::println);
    }

    private void BuscarLibros() {
       DatosLibros datos = getDatosLibros();
       Libros libro = new Libros(datos);
       repositorio.save(libro);
        System.out.println(datos);
    }
}
