INSERT INTO Perfil (nombre) VALUES
('ADMINISTRADOR'),
('MODERADOR'),
('USUARIO_NORMAL'),
('INVITADO');

INSERT INTO usuario (id, nombre, correo_electronico, fecha_creacion, contrasena) VALUES
(1, 'Usuario 1', 'usuario1@example.com', '2010-01-01 10:00:00', '123456'),
(2, 'Usuario 2', 'usuario2@example.com', '2010-01-01 10:00:00', '123456'),
(3, 'Usuario 3', 'usuario3@example.com', '2010-01-01 10:00:00', '123456'),
(4, 'Usuario 4', 'usuario4@example.com', '2010-01-01 10:00:00', '123456'),
(5, 'Usuario 5', 'usuario5@example.com', '2010-01-01 10:00:00', '123456'),
(6, 'Usuario 6', 'usuario6@example.com', '2010-01-01 10:00:00', '123456'),
(7, 'Usuario 7', 'usuario7@example.com', '2010-01-01 10:00:00', '123456'),
(8, 'Usuario 8', 'usuario8@example.com', '2010-01-01 10:00:00', '123456'),
(9, 'Usuario 9', 'usuario9@example.com', '2010-01-01 10:00:00', '123456'),
(10, 'Usuario 10', 'usuario10@example.com', '2010-01-01 10:00:00', '123456');

INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 1),
(6, 1), (6, 2),
(7, 2), (7, 3),
(8, 3), (8, 4),
(9, 1), (9, 3), (9, 4),
(10, 1), (10, 2), (10, 3);
