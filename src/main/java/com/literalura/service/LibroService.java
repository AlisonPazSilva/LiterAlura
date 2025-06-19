package com.literalura.service;

import com.literalura.client.GutendexClient;
import com.literalura.dto.DatosAutor;
import com.literalura.dto.DatosLibro;
import com.literalura.dto.ResultadoBusqueda;
import com.literalura.entity.Autor;
import com.literalura.entity.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {

    private final GutendexClient gutendexClient;
    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;

    public LibroService(GutendexClient gutendexClient, LibroRepository libroRepo, AutorRepository autorRepo) {
        this.gutendexClient = gutendexClient;
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public DatosLibro buscarLibroPorTitulo(String titulo) {
        ResultadoBusqueda resultado = gutendexClient.buscarLibrosPorTitulo(titulo);

        if (resultado != null && !resultado.results().isEmpty()) {
            System.out.println("📘 Libros encontrados:\n");

            resultado.results().stream().limit(5).forEach(libro -> {
                String idioma = libro.idiomas().isEmpty() ? "idioma no especificado" : libro.idiomas().get(0);
                int descargas = libro.numeroDeDescargas();
                String tituloLibro = libro.titulo();

                if (libro.autores().isEmpty()) {
                    System.out.printf("📖 %s\n👤 Autor: desconocido\n🌐 Idioma: %s\n📥 Descargas: %d\n\n",
                            tituloLibro, idioma, descargas);
                } else {
                    DatosAutor autor = libro.autores().get(0);
                    String nombre = autor.nombre();
                    String nacimiento = autor.anoNacimiento() != null ? autor.anoNacimiento().toString() : "¿?";
                    String fallecimiento = autor.anoFallecimiento() != null ? autor.anoFallecimiento().toString() : "¿?";

                    System.out.printf("📖 %s\n👤 Autor: %s (%s - %s)\n🌐 Idioma: %s\n📥 Descargas: %d\n\n",
                            tituloLibro, nombre, nacimiento, fallecimiento, idioma, descargas);
                }
            });

            DatosLibro datos = resultado.results().get(0);
            guardarLibroSiNoExiste(datos);
            return datos;
        }

        System.out.println("⚠️ No se encontraron resultados para ese título.");
        return null;
    }

    private void guardarLibroSiNoExiste(DatosLibro datosLibro) {
        boolean existe = libroRepo.findByTituloContainingIgnoreCase(datosLibro.titulo()).stream()
                .anyMatch(l -> l.getTitulo().equalsIgnoreCase(datosLibro.titulo()));
        if (existe) return;

        if (datosLibro.autores().isEmpty()) return;

        DatosAutor datosAutor = datosLibro.autores().get(0);
        Optional<Autor> autorExistente = autorRepo.findByNombreIgnoreCase(datosAutor.nombre());
        Autor autor;

        if (autorExistente.isPresent()) {
            autor = autorExistente.get();
        } else {
            autor = new Autor(datosAutor.nombre(), datosAutor.anoNacimiento(), datosAutor.anoFallecimiento());
            autor = autorRepo.save(autor);
        }

        Libro libro = new Libro(
                datosLibro.titulo(),
                datosLibro.idiomas().isEmpty() ? "desconocido" : datosLibro.idiomas().get(0),
                datosLibro.numeroDeDescargas(),
                autor
        );

        libroRepo.save(libro);
    }
}