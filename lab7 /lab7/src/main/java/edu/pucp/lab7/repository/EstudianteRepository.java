package edu.pucp.lab7.repository;

import edu.pucp.lab7.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByDni(String dni);
    boolean existsByDni(String dni);
    boolean existsByCodigoPUCP(String codigoPUCP);
}
