package com.literalura;

import com.literalura.dto.DatosLibro;
import com.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final LibroService libroService;

	// Inyección por constructor recomendada por Spring Boot
	public LiteraluraApplication(LibroService libroService) {
		this.libroService = libroService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el título de un libro:");
		String titulo = scanner.nextLine();

		DatosLibro libro = libroService.buscarLibroPorTitulo(titulo);

		if (libro != null) {
			System.out.println("Título: " + libro.titulo());

			if (libro.idiomas() != null && !libro.idiomas().isEmpty()) {
				System.out.println("Idioma: " + libro.idiomas().get(0));
			}

			System.out.println("Descargas: " + libro.numeroDeDescargas());

			if (libro.autores() != null && !libro.autores().isEmpty()) {
				System.out.println("Autor: " + libro.autores().get(0).nombre());
			}
		} else {
			System.out.println("No se encontró el libro.");
		}
	}
}