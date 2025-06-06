package edu.pucp.lab7.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;

    @Column(unique = true)
    private String dni;

    @Column(unique = true)
    private String codigoPUCP;

    private LocalDate fechaNacimiento;
    private String sexo;
    private String correoInstitucional;
    private String correoPersonal;
    private String telefono;
    private String direccion;
    private String departamento;
    private String provincia;
    private String carrera;
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaActualizacion;
    private Boolean estado;
}
