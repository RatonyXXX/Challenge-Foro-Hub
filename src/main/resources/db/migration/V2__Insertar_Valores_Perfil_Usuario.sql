INSERT INTO Perfil (nombre) VALUES
('ADMINISTRADOR'),
('MODERADOR'),
('USUARIO_NORMAL'),
('INVITADO');

INSERT INTO usuario (id, nombre, correo_electronico, fecha_creacion, contrasena) VALUES
(1, 'USUARIO01', 'usuario1@example.com', '2010-01-01 10:00:00', '123456'),
(2, 'USUARIO02', 'usuario2@example.com', '2010-01-01 10:00:00', '123456'),
(3, 'USUARIO03', 'usuario3@example.com', '2010-01-01 10:00:00', '123456'),
(4, 'USUARIO04', 'usuario4@example.com', '2010-01-01 10:00:00', '123456'),
(5, 'USUARIO05', 'usuario5@example.com', '2010-01-01 10:00:00', '123456'),
(6, 'USUARIO06', 'usuario6@example.com', '2010-01-01 10:00:00', '123456'),
(7, 'USUARIO07', 'usuario7@example.com', '2010-01-01 10:00:00', '123456'),
(8, 'USUARIO08', 'usuario8@example.com', '2010-01-01 10:00:00', '123456'),
(9, 'USUARIO09', 'usuario9@example.com', '2010-01-01 10:00:00', '123456'),
(10, 'USUARIO10', 'usuario10@example.com', '2010-01-01 10:00:00', '123456');


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
