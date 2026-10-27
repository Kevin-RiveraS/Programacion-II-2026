# Sistema de Reservas de Restaurante


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
* **Pruebas en Postman**
---
## Configuración y Base de Datos (PostgreSQL)

1. **Crear la Base de Datos:**  
   Abre tu gestor de PostgreSQL y crea una base de datos con el siguiente nombre:
   ```sql
   CREATE DATABASE restaurante_db;
   ```


---
## Modelo de Base de Datos
1. **Mesa:** `id` (PK), `numeroMesa` (Único), `capacidad`, `ubicacion`, `activa`.
2. **Reserva:** `id` (PK), `nombreCliente`, `telefonoCliente`, `fecha`, `hora`, `cantidadPersonas`, `estado`, `mesa_id` (FK).
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

## Endpoints y Pruebas Realizadas en Postman
​El proyecto cuenta con las siguientes pruebas funcionales ejecutadas y validadas mediante Postman:
>>http://localhost:8080/api/mesas y http://localhost:8080/api/reservas

## Pruebasy Evidencias en Postman

El backend cuenta con un conjunto de pruebas funcionales obligatorias para validar el comportamiento del sistema ante datos correctos y escenarios de error. Estas pruebas se ejecutan directamente en **Postman** y se validan con la base de datos en **PostgreSQL**:

### Pruebas para el Módulo de Mesas (`/api/mesas`) y Reservas (`/api/reservas`)

* **Registro correcto (`POST`)**
   * **Acción:** Enviar un objeto con información válida (ej. capacidad adecuada, datos completos).
   * **Resultado Esperado:** `201 Created` (o `200 OK`) y verificar que el registro se almacene correctamente en PostgreSQL.

* **Campos vacíos (`POST` / `PUT`)**
   * **Acción:** Enviar una solicitud omitiendo uno o más campos obligatorios.
   * **Resultado Esperado:** `400 Bad Request`, indicando claramente qué campos son inválidos gracias a las anotaciones `@Valid` y el manejo de excepciones.

* **Número inválido (`POST` / `PUT`)**
   * **Acción:** Enviar un número negativo o igual a cero en campos que exigen un valor positivo (como la capacidad de la mesa o la cantidad de personas).
   * **Resultado Esperado:** `400 Bad Request`.

* **Recurso existente (`GET`)**
   * **Acción:** Consultar un ID válido que sí se encuentre registrado en el sistema.
   * **Resultado Esperado:** `200 OK` retornando la información detallada del recurso.

* **Recurso inexistente (`GET`)**
   * **Acción:** Consultar un ID que no exista (ej. `GET /api/mesas/9999` o `/api/reservas/9999`).
   * **Resultado Esperado:** `404 Not Found`.

* **Actualización inválida (`PUT` / `PATCH`)**
   * **Acción:** Intentar actualizar un registro enviando información que incumpla las validaciones de negocio o reglas del sistema.
   * **Resultado Esperado:** `400 Bad Request`.

### Reglas de Negocio Específicas Validas
* **Control de Capacidad:** Rechazo (`400 Bad Request`) si la cantidad de personas supera la capacidad máxima de la mesa.
* **Mesa Inactiva:** Rechazo (`400 Bad Request`) Si la mesa no se encuentra Activa. 

---
> Autor: Kevin Oswaldo Rivera Sánchez                                                                     
> Carné: 9941-25-612
---
