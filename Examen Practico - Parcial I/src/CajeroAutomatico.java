import java.util.Scanner;

public class CajeroAutomatico {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String titular = "Kevin Oswaldo Rivera Sánchez";
        String numeroCuenta = "5612";
        int pinCorrecto = 2026;
        double saldo = 1000.00;
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
                case 6:
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
}