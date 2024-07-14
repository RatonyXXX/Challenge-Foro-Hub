# Sistema de Gestión de ForoHub

## Descripción
Este proyecto es un sistema de gestión de foros llamado ForoHub, desarrollado con Spring Boot y utilizando una arquitectura de microservicios. Permite la gestión de usuarios, perfiles, cursos, temas y respuestas en un entorno educativo o de discusión.

## Funcionalidades

### 1. Registro y Gestión de Usuarios
- **Registro de Usuarios**: Permite registrar nuevos usuarios con roles específicos.
- **Actualización de Perfiles**: Los usuarios pueden actualizar sus perfiles, incluyendo información personal.

### 2. Gestión de Cursos
- **Registro de Cursos**: Administradores pueden registrar nuevos cursos con detalles específicos.
- **Actualización de Cursos**: Capacidad para actualizar información detallada de los cursos existentes.

### 3. Gestión de Temas (Tópicos)
- **Registro de Temas**: Usuarios pueden crear nuevos temas de discusión.
- **Búsqueda y Filtrado**: Búsqueda y filtrado de temas por nombre de curso y año.

### 4. Gestión de Respuestas
- **Registro de Respuestas**: Usuarios pueden responder a temas existentes.
- **Eliminación de Respuestas**: Capacidad para eliminar respuestas según los permisos del usuario.

## Capturas de Pantalla

### Pantalla de Inicio
![Pantalla de Inicio](screenshots/home.png)

### Ejemplo de Registro de Usuario
![Registro de Usuario](screenshots/registro_usuario.png)

### Listado de Cursos
![Listado de Cursos](screenshots/listado_cursos.png)

### Detalle de Tema
![Detalle de Tema](screenshots/detalle_tema.png)

## Instalación y Uso
Para ejecutar localmente el proyecto:

1. Clona el repositorio: `git clone https://github.com/tu_usuario/tu_proyecto.git`
2. Importa el proyecto en tu IDE favorito.
3. Configura las dependencias y variables de entorno necesarias (por ejemplo, configuración de base de datos).
4. Ejecuta la aplicación desde tu IDE o utilizando Maven: `mvn spring-boot:run`
5. Accede a la aplicación desde tu navegador: `http://localhost:8080`

## Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Swagger UI para documentación de API

## Contribución
Contribuciones son bienvenidas. Si deseas contribuir a este proyecto, por favor realiza un fork y envía un pull request. Asegúrate de seguir las mejores prácticas y añadir pruebas si es necesario.

## Licencia
Este proyecto está licenciado bajo [MIT License](LICENSE).
