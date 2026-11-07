# Sistema de Reservas de Restaurante


>"SITUACIÓN": Un restaurante pequeño necesita administrar sus mesas y las reservaciones de los clientes.
>## El problema:
>Los restaurantes pequeños a menudo sufren para organizar sus mesas manualmente, lo que causa confusiones, llamadas perdidas, errores al anotar y sobreventa de espacios (dos grupos para la misma mesa a la misma hora). 
>¿Qué debe hacer el sistema para solucionarlo? 
> ## El alcance y regla:
>La aplicación automatiza este proceso permitiendo registrar mesas, controlar capacidades máximas, consultar disponibilidad en tiempo real y evitar cruces de horarios o duplicidad de reservas.
>1. La cantidad de personas no puede superar la capacidad de la mesa.
>2. No permitir dos reservas activas para la misma mesa, fecha y hora.
>3. Manejar los estados confirmada, atendida y cancelada.
   CONSULTAS E INDICADOR: Reservas por fecha, mesas disponibles, reservas confirmadas y ocupación estimada del día.
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
2. **Script de Creación de Tablas:**

    Ejecuta el siguiente script para crear la estructura relacional del sistema: 

```
CREATE TABLE mesa (
id BIGSERIAL PRIMARY KEY,
numero_mesa INT NOT NULL UNIQUE,
capacidad INT NOT NULL,
ubicacion VARCHAR(50) NOT NULL,
activa BOOLEAN DEFAULT TRUE
);

CREATE TABLE reserva (
id BIGSERIAL PRIMARY KEY,
nombre_cliente VARCHAR(100) NOT NULL,
telefono_cliente VARCHAR(20) NOT NULL,
fecha DATE NOT NULL,
hora TIME NOT NULL,
cantidad_personas INT NOT NULL,
estado VARCHAR(20) NOT NULL DEFAULT 'CONFIRMADA',
mesa_id BIGINT NOT NULL,
CONSTRAINT fk_reserva_mesa FOREIGN KEY (mesa_id) REFERENCES mesa(id)
);
```
---
## Modelo de Base de Datos
El sistema maneja una relación de uno a muchos entre mesa y reserva, a continuación se detalla la estructura de cada tabla:
### Tabla: `mesa`

| Columna | Tipo de Dato | Restricción / Atributo | Descripción                                     |
| :--- | :--- | :--- |:------------------------------------------------|
| `id` | `BIGSERIAL` | Primary Key | Identificador único autoincremental.            |
| `numero_mesa` | `INT` | `NOT NULL`, `UNIQUE` | Número físico asignado a la mesa.               |
| `capacidad` | `INT` | `NOT NULL` | Cantidad máxima de personas que soporta.        |
| `ubicacion` | `VARCHAR(50)` | `NOT NULL` | Zona del restaurante, seria Salón, Terraza etc. |
| `activa` | `BOOLEAN` | `DEFAULT TRUE` | Estado operativo de la mesa.                    |


### Tabla: `reserva`

| Columna | Tipo de Dato | Restricción / Atributo | Descripción                                                         |
| :--- | :--- | :--- |:--------------------------------------------------------------------|
| `id` | `BIGSERIAL` | Primary Key | Identificador único de la reserva.                                  |
| `nombre_cliente` | `VARCHAR(100)` | `NOT NULL` | Nombre completo de la persona que reserva.                          |
| `telefono_cliente` | `VARCHAR(20)` | `NOT NULL` | Teléfono de contacto del cliente.                                   |
| `fecha` | `DATE` | `NOT NULL` | Fecha programada para la reserva.                                   |
| `hora` | `TIME` | `NOT NULL` | Hora exacta de la reservación.                                      |
| `cantidad_personas`| `INT` | `NOT NULL` | Número de asistentes confirmados.                                   |
| `estado` | `VARCHAR(20)` | `DEFAULT 'CONFIRMADA'` | Estado actual (*CONFIRMADA*, *PENDIENTE*, *ATENDIDA*, *CANCELADA*). |
| `mesa_id` | `BIGINT` | Foreign Key (`mesa`) | Llave foránea que enlaza con la mesa asignada.                      |


---
## Arquitectura del Sistema
El proyecto se encuentra organizado bajo una arquitectura limpia por paquetes:
* `controller`: Controladores REST que exponen los endpoints (`/api/mesas`, `/api/reservas`).
* `service`: Contiene la lógica de negocio y validaciones del sistema.
* `repository`: Interfaces de Spring Data JPA para la persistencia en PostgreSQL.
* `entity`: Clases de persistencia mapeadas a la base de datos.
* `dto`: Objetos de transferencia de datos con anotaciones de validación (`@Valid`).
* `exception`: Manejador global de excepciones (`GlobalExceptionHandler`) para respuestas centralizadas (400, 404, 500).
* `PostgreSQL`: Base de Datos.
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
El proyecto cuenta con las siguientes pruebas funcionales ejecutadas y validadas mediante Postman:
>>http://localhost:8080/api/mesas y http://localhost:8080/api/reservas

## Pruebas y Evidencias en Postman

El backend cuenta con un conjunto de pruebas funcionales obligatorias para validar el comportamiento del sistema ante datos correctos y escenarios de error. Estas pruebas se ejecutan directamente en **Postman** y se validan con la base de datos en **PostgreSQL**:

### Pruebas para el Módulo de Mesas (`/api/mesas`) y Reservas (`/api/reservas`)

* **Registro correcto (`POST`)**
   * **Acción:** Enviar un objeto con información válida (ej. capacidad adecuada, datos completos).
   * **Resultado Esperado:** `201 Created` (o `200 OK`) y verificar que el registro se almacene correctamente en PostgreSQL.

* **Campos vacíos (`POST`)**
   * **Acción:** Enviar una solicitud omitiendo uno o más campos obligatorios.
   * **Resultado Esperado:** `400 Bad Request`, indicando claramente qué campos son inválidos gracias a las anotaciones `@Valid` y el manejo de excepciones.

* **Número inválido (`POST`)**
   * **Acción:** Enviar un número negativo o igual a cero en campos que exigen un valor positivo (como la capacidad de la mesa o la cantidad de personas).
   * **Resultado Esperado:** `400 Bad Request`.

* **Recurso existente (`GET`)**
   * **Acción:** Consultar un ID válido que sí se encuentre registrado en el sistema.
   * **Resultado Esperado:** `200 OK` retornando la información detallada del recurso.

* **Recurso inexistente (`GET`)**
   * **Acción:** Consultar un ID que no exista (ej. `GET /api/mesas/9999` o `/api/reservas/9999`).
   * **Resultado Esperado:** `404 Not Found`.

* **Actualización válida (`PUT` / `PATCH`)**
    * **Acción:** Intentar actualizar un registro enviando información que cumpla las validaciones de negocio o reglas del sistema.
    * **Resultado Esperado:** `200 ok`.

* **Actualización inválida (`PUT` / `PATCH`)**
   * **Acción:** Intentar actualizar un registro enviando información que incumpla las validaciones de negocio o reglas del sistema.
   * **Resultado Esperado:** `400 Bad Request`.

* **Eliminar un registro de mesa (`DELETE`)**
    * **Acción:** Intentar eliminar un registro con, id.
    * **Resultado Esperado:** `200 ok` o `404 ok` al poner un id, inexistente.

* **Conflicto de Estado (`PATCH` / `PUT`)** http://localhost:8080/api/reservas
    * **Acción:** Intentar modificar una reserva por el estado ya se encuentra como "CANCELADA".
    * **Resultado Esperado:** `409 Conflict`.

* **Cruce de Horarios Duplicidad (`POST`)** http://localhost:8080/api/reservas
    * **Acción:** Intentar registrar una nueva reserva para la misma mesa, misma fecha y misma hora de otra reserva ya existente (Activa).
    * **Resultado Esperado:** `400 Bad Request` indicando que la mesa ya se encuentra reservada en ese horario.

### Reglas de Negocio Específicas Válidas
* **Listado de mesas disponibles:** (`200 ok.`) Aplica al momento de un filtro basado en la fecha y hora para obtener nuestra disponibilidad de mesas, mostrando el resultado siguiente"¡Listado de mesas disponibles obtenido correctamente!".  
* **Control de Capacidad:** Rechazo (`400 Bad Request`) si la cantidad de personas supera la capacidad máxima de la mesa.
* **Mesa Inactiva:** Rechazo (`400 Bad Request`) Si la mesa no se encuentra Activa. 

---
## Instrucciones para abrir el Frontend
1. Ubica el archivo `index.html` en la carpeta del proyecto.
2. Haz doble clic sobre él o ábrelo directamente en tu navegador web preferido (Chrome, Edge, etc. enlace: http://localhost:8080/index.html) para interactuar con la interfaz gráfica conectada a la API REST.
---
> 
> Autor: Kevin Oswaldo Rivera Sánchez
> 
> Carné: 9941-25-612
---


