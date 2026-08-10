import java.util.Scanner;

public class CajeroAutomatico {


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String titular = "Kevin Oswaldo Rivera Sánchez ";
        String numeroCuenta = "5612";
        int pinCorrecto = 2026;

        double saldo = 1000.00;
        final double COMISION = 10.00;


        double saldoInicial = saldo;
        int depositosExitosos = 0;
        double totalDepositado = 0.0;
        int retirosExitosos = 0;
        double totalRetirado = 0.0;
        double totalComisiones = 0.0;
        int operacionesRechazadas = 0;
        int opcionesInvalidas = 0;


        boolean accesoConcedido = validarAcceso(pinCorrecto);

        if (!accesoConcedido) {
            System.out.println("\nCuenta bloqueada por superar el límite de intentos. Programa finalizado.");
            return;
        }

        System.out.println("\n¡Bienvenido/a, " + titular + "!");


        int opcion = 0;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");


            if (!scanner.hasNextInt()) {
                System.out.println("Error: Debe ingresar un número.");
                opcionesInvalidas++;
                scanner.next();
                continue;
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    consultarSaldo(titular, numeroCuenta, saldo);
                    break;

                case 2:

                    double deposito = procesarDeposito();
                    saldo += deposito;
                    depositosExitosos++;
                    totalDepositado += deposito;
                    break;

                case 3:

                    System.out.print("Ingrese el monto a retirar (múltiplo de Q20, max Q2000): Q");
                    double montoRetiroNormal = scanner.nextDouble();
                    double saldoAnteriorNorm = saldo;

                    saldo = realizarRetiro(montoRetiroNormal, saldo);

                    if (saldo < saldoAnteriorNorm) {
                        retirosExitosos++;
                        totalRetirado += montoRetiroNormal;
                    } else {
                        operacionesRechazadas++;
                    }
                    break;

                case 4:

                    System.out.print("Ingrese el monto a retirar de otra red: Q");
                    double montoRetiroCom = scanner.nextDouble();
                    double saldoAnteriorCom = saldo;

                    saldo = realizarRetiro(montoRetiroCom, saldo, COMISION);

                    if (saldo < saldoAnteriorCom) {
                        retirosExitosos++;
                        totalRetirado += montoRetiroCom;
                        totalComisiones += COMISION;
                    } else {
                        operacionesRechazadas++;
                    }
                    break;

                case 5:

                    mostrarResumen(saldoInicial, depositosExitosos, totalDepositado,
                            retirosExitosos, totalRetirado, totalComisiones,
                            operacionesRechazadas, opcionesInvalidas, saldo);
                    break;

                case 6:

                    System.out.println("\n=== RESUMEN FINAL DE LA SESIÓN ===");
                    mostrarResumen(saldoInicial, depositosExitosos, totalDepositado,
                            retirosExitosos, totalRetirado, totalComisiones,
                            operacionesRechazadas, opcionesInvalidas, saldo);
                    System.out.println("\nGracias por utilizar el cajero automático. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    opcionesInvalidas++;
                    continue;
            }

        } while (opcion != 6);
    }

    public static boolean validarAcceso(int pinCorrecto) {
        int maxIntentos = 3;
        for (int i = 1; i <= maxIntentos; i++) {
            System.out.print("Ingrese su PIN de 4 dígitos: ");
            int pinIngresado = scanner.nextInt();

            if (pinIngresado == pinCorrecto) {
                return true;
            } else {
                int restantes = maxIntentos - i;
                if (restantes > 0) {
                    System.out.println("PIN incorrecto. Intentos restantes: " + restantes);
                }
            }
        }
        return false;
    }


    public static void mostrarMenu() {
        System.out.println("\n----------------------------------");
        System.out.println("        CAJERO AUTOMÁTICO         ");
        System.out.println("----------------------------------");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Realizar retiro normal");
        System.out.println("4. Realizar retiro con comisión (Otra red)");
        System.out.println("5. Mostrar resumen de la sesión");
        System.out.println("6. Salir");
        System.out.println("----------------------------------");
    }


    public static void consultarSaldo(String titular, String numeroCuenta, double saldo) {
        System.out.println("\n--- CONSULTA DE SALDO ---");
        System.out.println("Titular: " + titular);
        System.out.println("Número de Cuenta: ****" + numeroCuenta);
        System.out.printf("Saldo disponible: Q%.2f\n", saldo);
    }


    public static double procesarDeposito() {
        double monto = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese el monto a depositar: Q");
            monto = scanner.nextDouble();

            if (monto <= 0) {
                System.out.println("El monto debe ser mayor a Q0.00.");
            } else if (monto > 5000.00) {
                System.out.println("El depósito no puede superar Q5,000.00 por operación.");
            } else {
                valido = true;
            }
        }
        System.out.printf("Depósito exitoso de Q%.2f.\n", monto);
        return monto;
    }

    public static double realizarRetiro(double monto, double saldoActual) {
        System.out.println("\n--- PROCESANDO RETIRO NORMAL ---");


        if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor a Q0.00.");
            return saldoActual;
        }
        if (monto % 20 != 0) {
            System.out.println("Error: El monto debe ser múltiplo de Q20.00.");
            return saldoActual;
        }
        if (monto > 2000.00) {
            System.out.println("Error: El monto máximo por retiro es de Q2,000.00.");
            return saldoActual;
        }
        if (monto > saldoActual) {
            System.out.println("Error: Fondos insuficientes.");
            return saldoActual;
        }


        double nuevoSaldo = saldoActual - monto;
        System.out.printf("Monto solicitado: Q%.2f\n", monto);
        System.out.printf("Saldo anterior: Q%.2f\n", saldoActual);
        System.out.printf("Total debitado: Q%.2f\n", monto);
        System.out.printf("Saldo actualizado: Q%.2f\n", nuevoSaldo);
        return nuevoSaldo;
    }

    public static double realizarRetiro(double monto, double saldoActual, double comision) {
        System.out.println("\n--- PROCESANDO RETIRO CON COMISIÓN ---");

        if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor a Q0.00.");
            return saldoActual;
        }
        if (monto % 20 != 0) {
            System.out.println("Error: El monto debe ser múltiplo de Q20.00.");
            return saldoActual;
        }
        if (monto > 2000.00) {
            System.out.println("Error: El monto máximo por retiro es de Q2,000.00.");
            return saldoActual;
        }

        double totalADebitar = monto + comision;

        if (totalADebitar > saldoActual) {
            System.out.println("Error: Fondos insuficientes para cubrir el retiro y la comisión de Q" + comision);
            return saldoActual;
        }

        double nuevoSaldo = saldoActual - totalADebitar;
        System.out.printf("Monto solicitado: Q%.2f\n", monto);
        System.out.printf("Comisión de red: Q%.2f\n", comision);
        System.out.printf("Total debitado del saldo: Q%.2f\n", totalADebitar);
        System.out.printf("Saldo anterior: Q%.2f\n", saldoActual);
        System.out.printf("Saldo actualizado: Q%.2f\n", nuevoSaldo);
        return nuevoSaldo;
    }

    public static void mostrarResumen(double saldoInicial, int depExitosos, double totDepositado,
                                      int retExitosos, double totRetirado, double totComisiones,
                                      int opRechazadas, int opInvalidas, double saldoActual) {
        System.out.println("\n==================================");
        System.out.println("       RESUMEN DE LA SESIÓN       ");
        System.out.println("==================================");
        System.out.printf("Saldo inicial: Q%.2f\n", saldoInicial);
        System.out.println("Cantidad de depósitos exitosos: " + depExitosos);
        System.out.printf("Total depositado: Q%.2f\n", totDepositado);
        System.out.println("Cantidad de retiros exitosos: " + retExitosos);
        System.out.printf("Total entregado en retiros: Q%.2f\n", totRetirado);
        System.out.printf("Total cobrado en comisiones: Q%.2f\n", totComisiones);
        System.out.println("Cantidad de operaciones rechazadas: " + opRechazadas);
        System.out.println("Cantidad de opciones inválidas: " + opInvalidas);
        System.out.printf("Saldo actual: Q%.2f\n", saldoActual);
        System.out.println("==================================");
    }
}