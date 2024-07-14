CREATE TABLE Perfil (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo_Electronico VARCHAR(100) NOT NULL UNIQUE,
    fecha_Creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    contrasena VARCHAR(100) NOT NULL
);

CREATE TABLE Usuario_Perfil (
    usuario_id INT NOT NULL,
    perfil_id INT NOT NULL
);

CREATE TABLE Curso (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    UNIQUE (nombre, categoria)
);

CREATE TABLE Topico (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_Creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    autor_id INT NOT NULL,
    curso_id INT NOT NULL,
    respuestas INT NOT NULL
--    UNIQUE (titulo, mensaje(255))
);

CREATE TABLE Respuesta (
    id SERIAL PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id INT NOT NULL,
    fecha_Creacion TIMESTAMP NOT NULL,
    autor_id INT NOT NULL,
    solucion BOOLEAN NOT NULL
);

