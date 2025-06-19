package com.literalura.repository;

import com.literalura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Autores vivos entre dos años
    List<Autor> findByAnoNacimientoLessThanEqualAndAnoFallecimientoGreaterThanEqual(Integer year1, Integer year2);

    // Autores vivos en año específico (muerte null o después del año)
    List<Autor> findByAnoNacimientoLessThanEqualAndAnoFallecimientoIsNullOrAnoFallecimientoGreaterThanEqual(Integer year1, Integer year2);

    // Buscar autores por nombre parcial
    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    // Buscar autor exacto por nombre
    Optional<Autor> findByNombreIgnoreCase(String nombre);
}