import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;


public class MainControlCompras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Producto> listaProductos = new ArrayList<>();
        HashSet<String> categoriasUnicas = new HashSet<>();
        HashMap<String, Double> totalPorCategoria = new HashMap<>();

        System.out.println("=== REGISTRO DE COMPRAS DEL HOGAR ===");
        System.out.println("Debe registrar como mínimo 5 productos válidos.\n");

        int productosValidos = 0;


        while (productosValidos < 5) {
            System.out.println("--- Producto #" + (productosValidos + 1) + " ---");

            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Precio unitario: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Cantidad comprada: ");
            int cantidad = Integer.parseInt(scanner.nextLine());





            if (nombre.trim().isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.\n");
            } else if (categoria.trim().isEmpty()) {
                System.out.println("Error: La categoría no puede estar vacía.\n");
            } else if (precio <= 0) {
                System.out.println("Producto no registrado: el precio debe ser mayor que cero.\n");
            } else if (cantidad <= 0) {
                System.out.println("Producto no registrado: la cantidad debe ser mayor que cero.\n");
            } else
            {


                Producto nuevoProducto = new Producto(nombre, categoria, precio, cantidad);


                listaProductos.add(nuevoProducto);



                categoriasUnicas.add(categoria);


                double subtotalActual = nuevoProducto.calcularSubtotal();
                if (totalPorCategoria.containsKey(categoria)) {
                    double acumulado = totalPorCategoria.get(categoria);
                    totalPorCategoria.put(categoria, acumulado + subtotalActual);
                } else {
                    totalPorCategoria.put(categoria, subtotalActual);
                }

                productosValidos++;
                System.out.println("¡Producto registrado con éxito!\n");
            }
        }



        System.out.println("\n===== RESUMEN DE COMPRAS =====");
        double totalGeneral = 0.0;



        for (Producto p : listaProductos) {
            double subtotal = p.calcularSubtotal();
            totalGeneral += subtotal;
            System.out.println(p.getNombre() + " | " + p.getCategoria() + " | Q" + p.getPrecioUnitario() + " x " + p.getCantidad() + " | Subtotal: Q" + subtotal);
        }



        System.out.println("\nCategorías registradas:");
        System.out.println(categoriasUnicas);



        System.out.println("\nTotal por categoría:");
        for (String cat : totalPorCategoria.keySet()) {
            System.out.println(cat + ": Q" + totalPorCategoria.get(cat));
        }

        System.out.println("\nProductos registrados: " + listaProductos.size());
        System.out.println("Total general: Q" + totalGeneral);


        Producto prodMayor = listaProductos.get(0);
        Producto prodMenor = listaProductos.get(0);

        for (Producto p : listaProductos) {
            if (p.calcularSubtotal() > prodMayor.calcularSubtotal()) {
                prodMayor = p;
            }
            if (p.calcularSubtotal() < prodMenor.calcularSubtotal()) {
                prodMenor = p;
            }
        }

        System.out.println("\nProducto con mayor gasto:");
        System.out.println(prodMayor.getNombre() + " - Q" + prodMayor.calcularSubtotal());

        System.out.println("\nProducto con menor gasto:");
        System.out.println(prodMenor.getNombre() + " - Q" + prodMenor.calcularSubtotal());


        String catMayorGasto = "";
        double maxGastoCat = -1.0;

        for (String cat : totalPorCategoria.keySet()) {
            double gasto = totalPorCategoria.get(cat);
            if (gasto > maxGastoCat) {
                maxGastoCat = gasto;
                catMayorGasto = cat;
            }
        }

        System.out.println("\nCategoría con mayor gasto:");
        System.out.println(catMayorGasto + " - Q" + maxGastoCat);



        System.out.println("\n==================================");
        System.out.print("Ingrese una categoría para consultar: ");
        String consulta = scanner.nextLine();

        if (totalPorCategoria.containsKey(consulta)) {
            System.out.println("Total gastado en " + consulta + ": Q" + totalPorCategoria.get(consulta));
        } else {
            System.out.println("La categoría ingresada no se encuentra registrada.");
        }

        scanner.close();
    }
}
