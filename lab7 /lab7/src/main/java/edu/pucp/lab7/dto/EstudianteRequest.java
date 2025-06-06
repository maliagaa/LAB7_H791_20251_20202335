package edu.pucp.lab7.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EstudianteRequest {
    private String nombres;
    private String apellidos;
    private String dni;
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
}
