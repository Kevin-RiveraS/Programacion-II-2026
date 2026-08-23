> * **Sistema de Gestión de Envíos en Java**


* >Nombre: "Kevin Oswaldo Rivera Sánchez"

Carné: "9941-25-612"

\-----------------------------------------------------------------------------------------------------------------------------------------


* > Descripción

Este Proyecto es una Aplicación de consola, orientada a objetos en Java desarrollada para automatizar el cálculo de costos de envíos nacionales e internacionales mediante una arquitectura basada en clases.


* > Objetivos

Desarrollar un Sistema funcional aplicando los pilares y conceptos de la programación orientada a objetos, (POO) en java, tales como encapsulamiento, herencia, sobre escritura, sobrecarga y polimorfismo, sin el uso de colecciones ni bases de datos. 




* >Temas Aplicados

Encapsulamiento: Protección de los atributos (`private`) en las clases y uso de métodos de acceso (`getters` y `setters`) con validaciones de datos.
* **Herencia:** Las clases `EnvioNacional` y `EnvioInternacional` extienden de la superclase base `Envio` para reutilizar atributos y métodos comunes.
* **Sobrescritura (@Override):** Adaptación del método `calcularCostoFinal()` en cada clase hija para aplicar las fórmulas específicas de cobro (distancia en envíos nacionales y cargo fijo con porcentaje en envíos internacionales).
* **Sobrecarga de Métodos:** Implementación de dos versiones del método `mostrarResumen()`: una sin parámetros para información general y otra con un parámetro booleano para mostrar el desglose de cálculos.
* **Polimorfismo:** Gestión de objetos creados como `EnvioNacional` o `EnvioInternacional` almacenándolos en variables de la superclase `Envio` para invocar sus métodos dinámicamente.


* >Estructura del Proyecto:
> Creación de la clase Envio.java:

* **(clase padre, donde lleva los atributos generales y métodos base**


Se utilizo en el primer avance lo siguiente: Clases y Objetos, Estructuración de la superclase `Envío` y sus subclases.

Encapsulamiento: Protección de atributos mediante modificadores de acceso (`private`) y métodos de acceso (`getters`/`setters`).

Sobrecarga de Métodos: Versiones múltiples de `mostrarResumen()` para visualización básica o con desglose.

Observaciones: se actualizo código, ya que en la ultima linea, falto mostrar el Costo Final.


* > Creación de la Clase de Envío Nacional:

* **(Clase donde contiene herencia, departamento, distancia y recargo)**

En este segundo avance se implemento la clase EnvioNacional.java, aplicando herencia al extender de la superclase Envio.java, además se agrego la lógica para calcular el costo final según la distancia y la sobre escritura correspondiente.


* >Creación de la Clase de Envío Internacional: 

* **(clase donde lleva herencia, pais destino, cuota fija y el porcentaje % de recargo)**

He creado la Clase EnvioInernacional.java que hereda de Envio.java, se agregaron los atributos de pais destino y el cargo fijo deL PRECIO Q 75.00 se sobreescribio el método "calcularCostoFinal", para aplicar el cargo fijo mas el 12% del costo base, y se implemento el resumen detallado.

* > Creacion de la Clase Main:

* **Aca se agrega el Menú interactivo, para laas validaciones de datos y ejecución.**


* >Instrucciones para Ejecturar el Programa:

1. Compilar los archivos de la carpeta "src: en la app Intellij IDEA,  abre la carpeta principal del proyecto llamado "Tarea IV SISTEMA DE GESTIÓN DE ENVIOS".
2. Click en la carpeta llamada "src"
3. Abre el archivo llamado "Main.java"
4. En la Aplicación de Intellij IDEA preciona Run sobre la clase llamado Main.java
5. Ingresar datos que solicitará el ménu. 

* >Al final mostrara el resultado esperado:
* **Envío Nacional (Ejemplo: Peso 5 kg, Distancia 120 km).**
Costo base: Q50.00
 Cargo por distancia: Q60.00
 Costo final: Q110.00
* **Envío Internacional (Ejemplo: Peso 3 kg)**
Costo base: Q30.00
Cargo fijo: Q75.00
Recargo 12%: Q3.60
Costo final: Q108.60


Observaciones: Si el usuario ingresa un peso o distancia menor o igual a 0, el programa muestra un mensaje de error y solicita el dato de nuevo sin cerrarse.


