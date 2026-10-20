# Sistema de Reservas de Restaurante - Avance 1

Proyecto backend desarrollado en **Spring Boot (Java 17)** y **PostgreSQL** para la gestión de mesas y reservas de un restaurante, correspondiente al primer avance del curso.

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
* **Spring Data JPA**
* **PostgreSQL**
* **Maven**

## Modelo de Datos (Entidades)
El sistema cuenta con dos entidades principales relacionadas mediante una asociación de muchos a uno (`@ManyToOne`):
1. **Mesa:** `id`, `numeroMesa`, `capacidad`, `ubicacion`, `activa`.
2. **Reserva:** `id`, `nombreCliente`, `telefonoCliente`, `fecha`, `hora`, `cantidadPersonas`, `estado`.

## Configuración y Conexión a Base de Datos
La conexión con PostgreSQL se configura en el archivo `src/main/resources/application.properties`:

```properties

spring.datasource.url=jdbc:postgresql://localhost:5432/restaurante_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
---
##

---
> Autor: Kevin Oswaldo Rivera Sánchez                                                                     
> Carné: 9941-25-612
---