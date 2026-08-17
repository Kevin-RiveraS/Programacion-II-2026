import java.util.Scanner;

public class Control_de_Parqueo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("nombre: Kevin Oswaldo Rivera Sánchez");
        System.out.println("Carne: 9941-25-612");

        System.out.println("=========================================");
        System.out.println("   SISTEMA DE CONTROL DE PARQUEO");
        System.out.println("=========================================\n");


        int cantidadVehiculos = 0;
        while (cantidadVehiculos <= 0) {
            System.out.print("Ingrese la cantidad de vehículos a registrar: ");
            cantidadVehiculos = scanner.nextInt();
            if (cantidadVehiculos <= 0) {
                System.out.println("Error: La cantidad debe ser mayor que cero.\n");
            }
        }
        scanner.nextLine();


        int cntMotos = 0;
        int cntAutos = 0;
        int cntPickups = 0;
        int cntTicketsPerdidos = 0;
        double totalRecaudado = 0.0;

        double pagoMaximo = -1.0;
        String placaPagoMaximo = "";


        for (int i = 1; i <= cantidadVehiculos; i++) {
            System.out.println("\n-----------------------------------------");
            System.out.println(" REGISTRO DEL VEHÍCULO #" + i);
            System.out.println("-----------------------------------------");

            System.out.print("Ingrese el número de placa: ");
            String placa = scanner.nextLine();


            int tipoVehiculo = 0;
            while (tipoVehiculo < 1 || tipoVehiculo > 3) {
                System.out.print("Tipo de vehículo (1: Motocicleta, 2: Automóvil, 3: Pickup/Camioneta): ");
                tipoVehiculo = scanner.nextInt();
                if (tipoVehiculo < 1 || tipoVehiculo > 3) {
                    System.out.println("Error: Debe ingresar una opción entre 1 y 3.");
                }
            }


            int horaEntrada = -1;
            while (horaEntrada < 0 || horaEntrada > 23) {
                System.out.print("Hora de entrada (0-23): ");
                horaEntrada = scanner.nextInt();
                if (horaEntrada < 0 || horaEntrada > 23) {
                    System.out.println("Error: Ingrese una hora válida entre 0 y 23.");
                }
            }

            int minEntrada = -1;
            while (minEntrada < 0 || minEntrada > 59) {
                System.out.print("Minuto de entrada (0-59): ");
                minEntrada = scanner.nextInt();
                if (minEntrada < 0 || minEntrada > 59) {
                    System.out.println("Error: Ingrese minutos válidos entre 0 y 59.");
                }
            }

            int horaSalida = -1;
            while (horaSalida < 0 || horaSalida > 23) {
                System.out.print("Hora de salida (0-23): ");
                horaSalida = scanner.nextInt();
                if (horaSalida < 0 || horaSalida > 23) {
                    System.out.println("Error: Ingrese una hora válida entre 0 y 23.");
                }
            }

            int minSalida = -1;
            while (minSalida < 0 || minSalida > 59) {
                System.out.print("Minuto de salida (0-59): ");
                minSalida = scanner.nextInt();
                if (minSalida < 0 || minSalida > 59) {
                    System.out.println("Error: Ingrese minutos válidos entre 0 y 59.");
                }
            }


            int minEntradaTotales = (horaEntrada * 60) + minEntrada;
            int minSalidaTotales = (horaSalida * 60) + minSalida;


            if (minSalidaTotales <= minEntradaTotales) {
                minSalidaTotales = minSalidaTotales + (24 * 60);
            }

            int duracionTotalMinutos = minSalidaTotales - minEntradaTotales;
            int horasTranscurridas = duracionTotalMinutos / 60;
            int minutosRestantes = duracionTotalMinutos % 60;


            int horasCobradas = horasTranscurridas;
            if (minutosRestantes > 0) {
                horasCobradas = horasCobradas + 1;
            }


            scanner.nextLine();
            String respTicket = "";
            while (!respTicket.equalsIgnoreCase("S") && !respTicket.equalsIgnoreCase("N")) {
                System.out.print("¿Perdió el ticket? (S/N): ");
                respTicket = scanner.nextLine();
                if (!respTicket.equalsIgnoreCase("S") && !respTicket.equalsIgnoreCase("N")) {
                    System.out.println("Error: Ingrese únicamente 'S' o 'N'.");
                }
            }


            double tarifaHora = obtenerTarifa(tipoVehiculo);
            double subtotal = horasCobradas * tarifaHora;
            double descuento = calcularDescuento(subtotal, horasCobradas);

            double recargo = 0.0;
            boolean perdioTicket = respTicket.equalsIgnoreCase("S");
            if (perdioTicket) {
                recargo = 50.0;
            }


            double totalPagar = 0.0;
            if (perdioTicket) {
                totalPagar = calcularPago(horasCobradas, tarifaHora, recargo);
            } else {
                totalPagar = calcularPago(horasCobradas, tarifaHora);
            }

            String nombreVehiculo = obtenerNombreVehiculo(tipoVehiculo);


            mostrarComprobante(placa, nombreVehiculo, horasTranscurridas, minutosRestantes,
                    horasCobradas, tarifaHora, subtotal, descuento, recargo, totalPagar);


            if (tipoVehiculo == 1) {
                cntMotos++;
            } else if (tipoVehiculo == 2) {
                cntAutos++;
            } else if (tipoVehiculo == 3) {
                cntPickups++;
            }

            if (perdioTicket) {
                cntTicketsPerdidos++;
            }

            totalRecaudado = totalRecaudado + totalPagar;

            if (totalPagar > pagoMaximo) {
                pagoMaximo = totalPagar;
                placaPagoMaximo = placa;
            }
        }


        System.out.println("\n=========================================");
        System.out.println("          RESUMEN DE LA JORNADA          ");
        System.out.println("=========================================");
        System.out.println("Cantidad de motocicletas: " + cntMotos);
        System.out.println("Cantidad de automóviles: " + cntAutos);
        System.out.println("Cantidad de pickups/camionetas: " + cntPickups);
        System.out.println("Cantidad de tickets perdidos: " + cntTicketsPerdidos);
        System.out.println("Total de dinero recaudado: Q" + totalRecaudado);
        if (pagoMaximo > 0) {
            System.out.println("Vehículo con pago más alto: " + placaPagoMaximo + " (Q" + pagoMaximo + ")");
        }
        System.out.println("=========================================\n");

        scanner.close();
    }



    public static double obtenerTarifa(int tipoVehiculo) {
        if (tipoVehiculo == 1) {
            return 5.00;
        } else if (tipoVehiculo == 2) {
            return 8.00;
        } else if (tipoVehiculo == 3) {
            return 12.00;
        }
        return 0.00;
    }

    public static String obtenerNombreVehiculo(int tipoVehiculo) {
        if (tipoVehiculo == 1) {
            return "Motocicleta";
        } else if (tipoVehiculo == 2) {
            return "Automóvil";
        } else if (tipoVehiculo == 3) {
            return "Pickup o camioneta";
        }
        return "Desconocido";
    }

    public static double calcularDescuento(double subtotal, int horas) {
        if (horas > 8) {
            return subtotal * 0.15;
        }
        return 0.00;
    }


    public static double calcularPago(int horas, double tarifa) {
        double subtotal = horas * tarifa;
        double descuento = calcularDescuento(subtotal, horas);
        return subtotal - descuento;
    }


    public static double calcularPago(int horas, double tarifa, double recargo) {
        double subtotal = horas * tarifa;
        double descuento = calcularDescuento(subtotal, horas);
        return (subtotal - descuento) + recargo;
    }

    public static void mostrarComprobante(String placa, String tipo, int horasReales, int minReales,
                                          int horasCobradas, double tarifa, double subtotal,
                                          double descuento, double recargo, double total) {
        System.out.println("\n========== COMPROBANTE ==========");
        System.out.println("Placa: " + placa);
        System.out.println("Tipo: " + tipo);
        System.out.println("Tiempo estacionado: " + horasReales + " horas y " + minReales + " minutos");
        System.out.println("Horas cobradas: " + horasCobradas);
        System.out.println("Tarifa por hora: Q" + tarifa);
        System.out.println("Subtotal: Q" + subtotal);
        System.out.println("Descuento: Q" + descuento);
        System.out.println("Recargo por ticket perdido: Q" + recargo);
        System.out.println("TOTAL: Q" + total);
        System.out.println("=================================\n");
    }
}