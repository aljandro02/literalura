package com.desafio.aluralibros;

import com.desafio.aluralibros.principal.Principal;
import com.desafio.aluralibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluralibrosApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AluralibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}
}
