package com.literalura.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer anoNacimiento;

    private Integer anoFallecimiento;

    public Autor() {}

    public Autor(String nombre, Integer anoNacimiento, Integer anoFallecimiento) {
        this.nombre = nombre;
        this.anoNacimiento = anoNacimiento;
        this.anoFallecimiento = anoFallecimiento;
    }

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getAnoNacimiento() { return anoNacimiento; }
    public void setAnoNacimiento(Integer anoNacimiento) { this.anoNacimiento = anoNacimiento; }

    public Integer getAnoFallecimiento() { return anoFallecimiento; }
    public void setAnoFallecimiento(Integer anoFallecimiento) { this.anoFallecimiento = anoFallecimiento; }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anoNacimiento=" + anoNacimiento +
                ", anoFallecimiento=" + anoFallecimiento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
