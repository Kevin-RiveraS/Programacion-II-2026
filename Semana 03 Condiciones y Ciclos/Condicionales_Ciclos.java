import java.util.Scanner;

public class Condicionales_Ciclos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;


        System.out.println("Estudiante: Kevin Rivera");
        System.out.println("Carné: 9941-25-612");
        System.out.println("Semana 3 — Condiciones y ciclos");


        do {
            System.out.println("\n========= DESAFÍOS LÓGICOS =========");
            System.out.println("1. Generar una secuencia");
            System.out.println("2. Realizar un conteo regresivo");
            System.out.println("3. Analizar números");
            System.out.println("4. Dibujar una pirámide");
            System.out.println("5. Validar palabra secreta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Número inicial: ");
                    int inicio = sc.nextInt();
                    System.out.print("Número final: ");
                    int fin = sc.nextInt();
                    System.out.print("Incremento: ");
                    int inc = sc.nextInt();

                    if (inc > 0 && fin > inicio) {
                        for (int i = inicio; i <= fin; i += inc) {
                            System.out.print(i + " ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("Datos inválidos. No se generó la secuencia.");
                    }
                    break;

                case 2:
                    int num;
                    do {
                        System.out.print("Ingrese un número entre 10 y 50: ");
                        num = sc.nextInt();
                    } while (num < 10 || num > 50);

                    while (num >= 0) {
                        System.out.print(num + " ");
                        num--;
                    }
                    System.out.println("\n¡Despegue!");
                    break;

                case 3:
                    int positivos = 0, negativos = 0, suma = 0, ignorados = 0;
                    while (true) {
                        System.out.print("Ingrese un número (0 para terminar): ");
                        int n = sc.nextInt();

                        if (n == 0) {
                            break;
                        }

                        if (n % 5 == 0) {
                            System.out.println("El número " + n + " fue ignorado.");
                            ignorados++;
                            continue;
                        }

                        if (n > 0) {
                            positivos++;
                        } else {
                            negativos++;
                        }

                        suma += n;
                    }

                    System.out.println("Positivos: " + positivos);
                    System.out.println("Negativos: " + negativos);
                    System.out.println("Suma válida: " + suma);
                    System.out.println("Números ignorados: " + ignorados);
                    break;

                case 4:
                    System.out.print("Ingrese la altura (3-10): ");
                    int altura = sc.nextInt();

                    if (altura >= 3 && altura <= 10) {
                        for (int fila = 1; fila <= altura; fila++) {
                            for (int espacio = 1; espacio <= altura - fila; espacio++) {
                                System.out.print(" ");
                            }
                            for (int estrella = 1; estrella <= (2 * fila - 1); estrella++) {
                                System.out.print("*");
                            }
                            System.out.println();
                        }
                    } else {
                        System.out.println("Altura inválida.");
                    }
                    break;

                case 5:
                    String palabra;
                    do {
                        System.out.print("Ingrese la palabra secreta: ");
                        palabra = sc.nextLine().trim();
                        if (!palabra.equalsIgnoreCase("Guatemala")) {
                            System.out.println("Palabra incorrecta. Intente nuevamente.");
                        }
                    } while (!palabra.equalsIgnoreCase("Guatemala"));
                    System.out.println("Palabra correcta.");
                    break;

                case 6:
                    System.out.println("Programa finalizado correctamente.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 6);

        sc.close();
    }
}