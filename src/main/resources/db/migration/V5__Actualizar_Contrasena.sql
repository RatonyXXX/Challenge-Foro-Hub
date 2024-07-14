UPDATE usuario SET contrasena='$2a$10$GzZgUNyjAN7eutkwiOWQrecJK7VojkZsMQRXDd3BH3RpuUh/rLVgW' WHERE id BETWEEN 1 AND 5;

UPDATE topico t
SET respuestas = (
    SELECT COUNT(*)
    FROM respuesta r
    WHERE r.topico_id = t.id
);