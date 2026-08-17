# Sistema de Control de Parqueo

* **Nombre Completo:** [Kevin Oswaldo Rivera Sánchez]
* **Número de Carné:** [9941-25-612]

---

## Descripción Breve de la Solución
Este programa gestiona el cobro y control de un estacionamiento público. Fue desarrollado utilizando estructuras fundamentales de programación como estructuras condicionales `if / else if`, ciclos `while` para la validación de datos de entrada y un ciclo `for` para iterar sobre cada vehículo registrado.

El sistema calcula la tarifa correspondiente según el tipo de vehículo, aplica un descuento del 15% cuando la permanencia supera las 8 horas, gestiona recargos por pérdida de ticket e imprime un resumen al final de la jornada.

---

## Métodos Creados y Función de Cada Uno

1. **`obtenerTarifa(int tipoVehiculo)`**
   Devuelve el costo por hora según la opción seleccionada (1: Q5.00, 2: Q8.00, 3: Q12.00) utilizando condicionales `if / else if`.

2. **`obtenerNombreVehiculo(int tipoVehiculo)`**
   Convierte el número del tipo de vehículo a su nombre en texto ("Motocicleta", "Automóvil", "Pickup o camioneta").

3. **`calcularDescuento(double subtotal, int horas)`**
   Comprueba si el tiempo cobrado es mayor a 8 horas (`horas > 8`). De ser así, calcula el 15% de descuento sobre el subtotal.

4. **`calcularPago(int horas, double tarifa)`**
   Primera versión del método sobrecargado. Calcula el total a pagar cuando **NO** se perdió el ticket (Subtotal - Descuento).

5. **`calcularPago(int horas, double tarifa, double recargo)`**
   Segunda versión del método sobrecargado. Calcula el total a pagar cuando **SÍ** se perdió el ticket, sumando el recargo de Q50.00.

6. **`mostrarComprobante(...)`**
   Método `void` encargado de imprimir en pantalla el desglose del cobro del vehículo actual.

---

## Explicación de la Sobrecarga de Métodos
La sobrecarga de métodos ocurre cuando dos o más métodos dentro de la misma clase tienen el **mismo nombre pero diferente lista de parámetros**.

En este programa se aplicó en el método `calcularPago`:
* `calcularPago(int horas, double tarifa)` $\rightarrow$ Recibe 2 parámetros (usado cuando el ticket NO se perdió).
* `calcularPago(int horas, double tarifa, double recargo)` $\rightarrow$ Recibe 3 parámetros (usado cuando SÍ se perdió el ticket).

El programa decide automáticamente qué versión ejecutar según los datos que le enviemos.

---

## Reto Opcional Implementado (Control Exacto del Tiempo)
**Sí, se realizó el reto opcional.**

* **Conversión a minutos:** La hora y minutos de entrada y salida se convierten a minutos totales del día (`hora * 60 + minuto`).
* **Cruce de Medianoche:** Si los minutos de salida son menores o iguales a los de entrada (indica que el vehículo salió al día siguiente), se le suman `1440` minutos (equivalentes a 24 horas).
* **Redondeo de horas:** Si al dividir la duración total quedan minutos restantes (`minutosRestantes > 0`), se suma 1 hora adicional al total cobrado.

---

## Casos de Prueba Utilizados

### Caso 1: Cobro Normal (Sin descuento, sin ticket perdido)
* **Entrada:** Placa `P001AAA`, Tipo `2` (Auto), Entrada `08:00`, Salida `12:00` (4 horas), Ticket `N`.
* **Resultado:** Subtotal: Q32.00 | Descuento: Q0.00 | Recargo: Q0.00 | **TOTAL: Q32.00**.

### Caso 2: Cruce de Medianoche + Descuento + Ticket Perdido
* **Entrada:** Placa `P002BBB`, Tipo `3` (Pickup), Entrada `22:30`, Salida `08:10` (9h y 40m $\rightarrow$ 10 horas cobradas), Ticket `S`.
* **Resultado:** Subtotal: Q120.00 | Descuento: Q18.00 | Recargo: Q50.00 | **TOTAL: Q152.00**.

### Caso 3: Validaciones de Entrada (Datos Incorrectos)
* Se ingresan valores inválidos como tipo de vehículo `5`, horas fuera de rango (`25`) o respuestas distintas a `S` o `N`. El sistema muestra el mensaje de error correspondiente y vuelve a solicitar el dato sin detener el programa.
