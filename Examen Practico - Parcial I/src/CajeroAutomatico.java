import java.util.Scanner;

public class CajeroAutomatico {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String titular = "Kevin Oswaldo Rivera Sánchez";
        String numeroCuenta = "5612";
        int pinCorrecto = 2026;

        boolean accesoConcedido = validarAcceso(pinCorrecto);

        if (!accesoConcedido) {
            System.out.println("\nCuenta bloqueada por superar el límite de intentos. Programa finalizado.");
            return;
        }

        System.out.println("\n¡Bienvenido/a, " + titular + "!");
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
}