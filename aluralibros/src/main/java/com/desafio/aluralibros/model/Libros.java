package com.desafio.aluralibros.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "Libro")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id;
    @Column(unique = true)
  private   String titulo;
  private List<DatosAutor> autores;
  private   List<String> idioma;
  private   Double numeroDeDescargas;
  @ManyToOne
  @JoinColumn(name = "autor_id")
  private Autor autor;

  public Libros(DatosLibros datosLibros){
      this.titulo = datosLibros.titulo();
      this.autores = datosLibros.autor();
      this.idioma = datosLibros.idioma();
      this.numeroDeDescargas = datosLibros.numeroDeDescargas();
  }
  public Libros(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public List<DatosAutor> getAutor() {
        return autores;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autores = autor;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma=" + idioma +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }
}
