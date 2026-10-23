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

# Sistema de Gestión de Reservas de Restaurante "operaciones del backend"

API REST desarrollada en Java con **Spring Boot** y **PostgreSQL** para la gestión de mesas y reservaciones en un restaurante. Este proyecto forma parte de la entrega académica de desarrollo backend.

## Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Framework:** Spring Boot
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Pruebas de API:** Postman

---

## ️Modelo de Base de Datos

El sistema maneja dos entidades principales:

1. **Mesa (`Entity`):**
   * `id` (PK, Autoincremental)
   * `numeroMesa` (Único)
   * `capacidad`
   * `ubicacion`
   * `activa` (Boolean)

2. **Reserva (`Entity`):**
   * `id` (PK, Autoincremental)
   * `nombreCliente`
   * `telefonoCliente`
   * `fecha`
   * `hora`
   * `cantidadPersonas`
   * `estado`
   * `mesa` (Relación `@ManyToOne` con Mesa)

---

##  Endpoints Principales

### Mesas (`/api/mesas`)
* `POST /api/mesas` - Registra una nueva mesa.
`GET /api/mesas` - Obtiene el listado completo de mesas.

### Reservas (`/api/reservas`)
* `POST /api/reservas` - Registra una nueva reservación asociada a una mesa existente.
* `GET /api/reservas` - Obtiene el listado de reservaciones.

---

## Validaciones y Reglas de Negocio

1. **El Número de Mesa:** No se permite el registro de dos mesas con el mismo `numeroMesa`.
2. **Control de Duplicidad en Reservas:** El sistema previene el solapamiento de reservaciones bloqueando múltiples reservas para la misma mesa en una misma fecha y hora.

---

##  Evidencias y Pruebas (Postman)

El proyecto incluye datos de prueba precargados en PostgreSQL:
* **Entidad Principal (Mesa):** 10 registros activos.
* **Entidad de Apoyo (Reserva):** 5 reservaciones registradas.

---

> Autor: Kevin Oswaldo Rivera Sánchez                                                                     
> Carné: 9941-25-612
---