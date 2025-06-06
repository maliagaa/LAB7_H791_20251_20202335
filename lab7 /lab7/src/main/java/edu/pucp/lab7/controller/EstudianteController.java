package edu.pucp.lab7.controller;

import edu.pucp.lab7.dto.EstudianteRequest;
import edu.pucp.lab7.dto.EstudianteResponse;
import edu.pucp.lab7.entity.Estudiante;
import edu.pucp.lab7.repository.EstudianteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Listar todos los estudiantes
    @GetMapping
    public List<EstudianteResponse> listarEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Obtener estudiante por DNI
    @GetMapping("/{dni}")
    public ResponseEntity<?> obtenerPorDni(@PathVariable String dni) {
        Optional<Estudiante> optional = estudianteRepository.findByDni(dni);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Estudiante no encontrado");
        }
        return ResponseEntity.ok(mapToResponse(optional.get()));
    }

    // Registrar estudiante
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody EstudianteRequest request) {
        if (estudianteRepository.existsByDni(request.getDni())) {
            return ResponseEntity.badRequest().body("El DNI ya está registrado");
        }
        if (estudianteRepository.existsByCodigoPUCP(request.getCodigoPUCP())) {
            return ResponseEntity.badRequest().body("El Código PUCP ya está registrado");
        }

        Estudiante estudiante = mapToEntity(request);
        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setUltimaActualizacion(null);
        estudiante.setEstado(true);

        estudianteRepository.save(estudiante);
        return ResponseEntity.status(201).body("Estudiante registrado correctamente");
    }

    // Actualizar estudiante
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteRequest request) {
        Optional<Estudiante> optional = estudianteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Estudiante no encontrado");
        }

        Estudiante estudiante = optional.get();

        estudiante.setNombres(request.getNombres());
        estudiante.setApellidos(request.getApellidos());
        estudiante.setFechaNacimiento(request.getFechaNacimiento());
        estudiante.setSexo(request.getSexo());
        estudiante.setCorreoInstitucional(request.getCorreoInstitucional());
        estudiante.setCorreoPersonal(request.getCorreoPersonal());
        estudiante.setTelefono(request.getTelefono());
        estudiante.setDireccion(request.getDireccion());
        estudiante.setDepartamento(request.getDepartamento());
        estudiante.setProvincia(request.getProvincia());
        estudiante.setCarrera(request.getCarrera());
        estudiante.setUltimaActualizacion(LocalDateTime.now());

        estudianteRepository.save(estudiante);
        return ResponseEntity.ok("Estudiante actualizado correctamente");
    }

    // Borrado lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Estudiante> optional = estudianteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Estudiante no encontrado");
        }
        Estudiante estudiante = optional.get();
        estudiante.setEstado(false);
        estudiante.setUltimaActualizacion(LocalDateTime.now());
        estudianteRepository.save(estudiante);

        return ResponseEntity.ok("Estudiante eliminado lógicamente");
    }

    private EstudianteResponse mapToResponse(Estudiante est) {
        EstudianteResponse dto = new EstudianteResponse();
        dto.setId(est.getId());
        dto.setNombres(est.getNombres());
        dto.setApellidos(est.getApellidos());
        dto.setDni(est.getDni());
        dto.setCodigoPUCP(est.getCodigoPUCP());
        dto.setFechaNacimiento(est.getFechaNacimiento());
        dto.setSexo(est.getSexo());
        dto.setCorreoInstitucional(est.getCorreoInstitucional());
        dto.setCorreoPersonal(est.getCorreoPersonal());
        dto.setTelefono(est.getTelefono());
        dto.setDireccion(est.getDireccion());
        dto.setDepartamento(est.getDepartamento());
        dto.setProvincia(est.getProvincia());
        dto.setCarrera(est.getCarrera());
        dto.setEstado(est.getEstado() != null && est.getEstado() ? "Activo" : "Inactivo");
        return dto;
    }

    private Estudiante mapToEntity(EstudianteRequest req) {
        Estudiante e = new Estudiante();
        e.setNombres(req.getNombres());
        e.setApellidos(req.getApellidos());
        e.setDni(req.getDni());
        e.setCodigoPUCP(req.getCodigoPUCP());
        e.setFechaNacimiento(req.getFechaNacimiento());
        e.setSexo(req.getSexo());
        e.setCorreoInstitucional(req.getCorreoInstitucional());
        e.setCorreoPersonal(req.getCorreoPersonal());
        e.setTelefono(req.getTelefono());
        e.setDireccion(req.getDireccion());
        e.setDepartamento(req.getDepartamento());
        e.setProvincia(req.getProvincia());
        e.setCarrera(req.getCarrera());
        return e;
    }
}
