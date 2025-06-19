package com.literalura.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Integer numeroDescargas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(String titulo, String idioma, Integer numeroDescargas, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;
        this.autor = autor;
    }

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getNumeroDescargas() { return numeroDescargas; }
    public void setNumeroDescargas(Integer numeroDescargas) { this.numeroDescargas = numeroDescargas; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                ", autor=" + autor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

