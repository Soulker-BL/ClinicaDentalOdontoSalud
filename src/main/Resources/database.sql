CREATE DATABASE IF NOT EXISTS odontosalud;
USE odontosalud;

CREATE TABLE IF NOT EXISTS usuario(
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS paciente(
    id_paciente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(8) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(150),
    email VARCHAR(100),
    genero VARCHAR(20),
    fecha_nacimiento DATE
);

CREATE TABLE IF NOT EXISTS odontologo(
    id_odontologo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    especialidad VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS cita(
    id_cita BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(30),
    id_paciente BIGINT,
    id_odontologo BIGINT,
    FOREIGN KEY(id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY(id_odontologo) REFERENCES odontologo(id_odontologo)
);

CREATE TABLE IF NOT EXISTS historial_clinico(
    id_historial BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_apertura DATE,
    observaciones TEXT,
    antecedentes TEXT,
    id_paciente BIGINT UNIQUE,
    FOREIGN KEY(id_paciente) REFERENCES paciente(id_paciente)
);

CREATE TABLE IF NOT EXISTS consulta_medica(
    id_consulta BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_consulta DATE,
    diagnostico TEXT,
    observaciones TEXT,
    id_historial BIGINT,
    id_odontologo BIGINT,
    FOREIGN KEY(id_historial) REFERENCES historial_clinico(id_historial),
    FOREIGN KEY(id_odontologo) REFERENCES odontologo(id_odontologo)
);

CREATE TABLE IF NOT EXISTS tratamiento(
    id_tratamiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_tratamiento VARCHAR(150),
    descripcion TEXT,
    costo DECIMAL(10,2),
    estado VARCHAR(30),
    id_consulta BIGINT,
    FOREIGN KEY(id_consulta) REFERENCES consulta_medica(id_consulta)
);

CREATE TABLE IF NOT EXISTS receta(
    id_receta BIGINT AUTO_INCREMENT PRIMARY KEY,
    medicamento VARCHAR(150),
    indicaciones TEXT,
    fecha DATE,
    id_consulta BIGINT,
    FOREIGN KEY(id_consulta) REFERENCES consulta_medica(id_consulta)
);

CREATE TABLE IF NOT EXISTS pago(
    id_pago BIGINT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(10,2),
    fecha_pago DATE,
    metodo_pago VARCHAR(50),
    id_paciente BIGINT,
    FOREIGN KEY(id_paciente) REFERENCES paciente(id_paciente)
);

CREATE TABLE IF NOT EXISTS registro_actividad(
    id_registro BIGINT AUTO_INCREMENT PRIMARY KEY,
    accion VARCHAR(150),
    fecha_hora DATETIME,
    id_usuario BIGINT,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

INSERT INTO usuario(username, password, rol)
SELECT 'Alex@gmail.com', 'alex1234', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM usuario WHERE username = 'Alex@gmail.com'
);
