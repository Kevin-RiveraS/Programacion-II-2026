# Sistema de Reservas de Restaurante 

Proyecto backend y frontend desarrollado en **Spring Boot (Java 17)** y **PostgreSQL** para la gestión de mesas y reservas de un restaurante, correspondiente al primer avance del curso.

>Descripción: "SITUACIÓN: Un restaurante pequeño necesita administrar sus mesas y las reservaciones de los clientes. El sistema debe impedir reservaciones incompatibles y facilitar la consulta de mesas disponibles.
ENTIDADES Y RELACIÓN: Mesa y Reserva. Una mesa puede tener muchas reservas en diferentes horarios.
REGLAS PRINCIPALES:
1. La cantidad de personas no puede superar la capacidad de la mesa.
2. No permitir dos reservas activas para la misma mesa, fecha y hora.
3. Manejar los estados confirmada, atendida y cancelada.
   CONSULTAS E INDICADOR: Reservas por fecha, mesas disponibles, reservas confirmadas y ocupación estimada del día."
##  Descripción del Proyecto
Este sistema permite administrar la disponibilidad de mesas y el registro de reservaciones de los clientes mediante una arquitectura limpia basada en capas y persistencia con JPA/Hibernate.

## ️ Tecnologías Utilizadas
* **Java 17**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **PostgreSQL**
* **Maven**
* **Frontend: HTML, CSS Y JavaScript**
* **Pruebas en Postman**

---

## Arquitectura del Sistema
El proyecto se encuentra organizado bajo una arquitectura limpia por paquetes:
* `controller`: Controladores REST que exponen los endpoints (`/api/mesas`, `/api/reservas`).
* `service`: Contiene la lógica de negocio y validaciones del sistema.
* `repository`: Interfaces de Spring Data JPA para la persistencia en PostgreSQL.
* `entity`: Clases de persistencia mapeadas a la base de datos.
* `dto`: Objetos de transferencia de datos con anotaciones de validación (`@Valid`).
* `exception`: Manejador global de excepciones (`GlobalExceptionHandler`) para respuestas centralizadas (400, 404, 500).

---
## Modelo de Base de Datos
1. **Mesa:** `id` (PK), `numeroMesa` (Único), `capacidad`, `ubicacion`, `activa`.
2. **Reserva:** `id` (PK), `nombreCliente`, `telefonoCliente`, `fecha`, `hora`, `cantidadPersonas`, `estado`, `mesa_id` (FK).

---
## Instrucciones para Ejecutar el Backend

1. **Abrir el proyecto:**
   Abre tu entorno de desarrollo (IDE) favorito, como IntelliJ IDEA, y carga el proyecto como un proyecto Maven existente.

2. **Configurar la Base de Datos:**
   Asegúrate de tener PostgreSQL activo en tu computadora y crea una base de datos con el nombre `restaurante_db`. Verifica que tus credenciales (usuario y contraseña) en el archivo `src/main/resources/application.properties` coincidan con las de tu entorno local.

3. **Ejecutar la Aplicación:**
   Busca la clase principal del proyecto llamada **`RestauranteApplication.java`** (normalmente ubicada en el paquete principal de `src/main/java`). Haz clic derecho sobre ella y selecciona la opción de ejecutar (**Run**).

4. **Verificar el Servidor:**
   Una vez que la consola indique que Spring Boot ha iniciado correctamente, la API REST estará disponible y escuchando en el puerto por defecto:
   `http://localhost:8080`
---
##  Evidencias y Pruebas (Postman)


El proyecto incluye datos de prueba precargados en PostgreSQL:
* **Entidad Principal (Mesa): 10 registros iniciales**.
* **Entidad de Apoyo (Reserva) 05 registros iniciales**.
* **Ingresar: http://localhost:8080/api/mesas para realizar las pruebas**.

---
> Autor: Kevin Oswaldo Rivera Sánchez                                                                     
> Carné: 9941-25-612
---
