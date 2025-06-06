CREATE TABLE estudiante (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nombres VARCHAR(100) NOT NULL,
                            apellidos VARCHAR(100) NOT NULL,
                            dni CHAR(8) NOT NULL UNIQUE,
                            codigo_pucp CHAR(8) NOT NULL UNIQUE,
                            fecha_nacimiento DATE NOT NULL,
                            sexo CHAR(1) NOT NULL CHECK (sexo IN ('M', 'F')),
                            correo_institucional VARCHAR(150) NOT NULL,
                            correo_personal VARCHAR(150) NOT NULL,
                            telefono CHAR(9) NOT NULL,
                            direccion VARCHAR(100),
                            departamento VARCHAR(100) NOT NULL,
                            provincia VARCHAR(100) NOT NULL,
                            carrera VARCHAR(100) NOT NULL,
                            fecha_registro DATETIME,
                            ultima_actualizacion DATETIME,
                            estado BOOLEAN DEFAULT TRUE
);
