package com.desafio.aluralibros.model;

import jakarta.persistence.*;

import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  private   String nombre;
  private int fechaNacimiento;
  private int fechaDeMuerte;
    @OneToMany(mappedBy ="autor",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libros> libro;
    public Autor() {}

    public Autor(String nombre, int fechaNacimiento, int fechaDeMuerte){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<Libros> getLibro() {
        return libro;
    }

    public void setLibro(List<Libros> libro) {
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", fechaDeMuerte='" + fechaDeMuerte + '\'' +
                '}';
    }
}
