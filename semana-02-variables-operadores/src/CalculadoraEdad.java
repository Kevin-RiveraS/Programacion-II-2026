import java.util.Scanner;

public class CalculadoraEdad {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = scanner.nextLine();

        System.out.print("Ingrese su año de nacimiento: ");
        int anioNacimiento = scanner.nextInt();

        System.out.print("Ingrese el año actual: ");
        int anioActual = scanner.nextInt();


        int edadAproximada = anioActual - anioNacimiento;
        int edadEnMeses = edadAproximada * 12;
        boolean esMayorDeEdad = edadAproximada >= 18;


        System.out.println("\n----- RESULTADO -----");
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Edad aproximada: " + edadAproximada + " años");
        System.out.println("Edad aproximada en meses: " + edadEnMeses + " meses");
        System.out.println("¿Es mayor de edad?: " + esMayorDeEdad);

        scanner.close();


        System.out.println("\nEstudiante: Kevin Oswaldo Rivera Sánchez  - Carné: 9941-25-612");
    }
}