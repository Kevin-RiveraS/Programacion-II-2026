Sistema de Gestión de Envíos en Java
Nombre: "Kevin Oswaldo Rivera Sánchez"

Carné: "9941-25-612"

\--------------------------------------------------------------------------------------------------------------------------------

> Descripción

Aplicación orientada a objetos en Java desarrollada para automatizar el cálculo de costos de envíos nacionales e internacionales mediante una arquitectura basada en clases.



>Temas Aplicados

 > Creación de la clase Envio.java:

Se utilizo en el primer avance lo siguiente: Clases y Objetos, Estructuración de la superclase `Envío` y sus subclases.

Encapsulamiento: Protección de atributos mediante modificadores de acceso (`private`) y métodos de acceso (`getters`/`setters`).

Sobrecarga de Métodos: Versiones múltiples de `mostrarResumen()` para visualización básica o con desglose.

Observaciones: se actualizo código, ya que en la ultima linea, falto mostrar el Costo Final.


> Creación de la Clase de Envío Nacional:
En este segundo avance se implemento la clase EnvioNacional.java, aplicando herencia al extender de la superclase Envio.java, además se agrego la lógica para calcular el costo final según la distancia y la sobre escritura correspondiente.



>Estructura del Proyecto:

src/

-- Envio.java
-- EnvioNacional.java
