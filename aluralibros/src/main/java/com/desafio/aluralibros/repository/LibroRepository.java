package com.desafio.aluralibros.repository;

import com.desafio.aluralibros.model.Autor;
import com.desafio.aluralibros.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LibroRepository extends JpaRepository<Libros,Long> {
    @Query("SELECT s FROM Autor a WHERE death_year < :fecha")
    List<Autor> buscarAutoresVivos(int fecha);
}
