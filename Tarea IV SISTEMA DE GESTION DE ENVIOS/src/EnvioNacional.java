import java.util.Scanner;

public class EnvioNacional extends Envio {
    private String departamento;
    private double distancia;

    public EnvioNacional(String codigo, String destinatario, double peso, String departamento, double distancia) {
        super(codigo, destinatario, peso);
        this.departamento = departamento;
        this.distancia = distancia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public double calcularCostoFinal() {
        double cargoDistancia = distancia * 0.50;
        return calcularCostoBase() + cargoDistancia;
    }

    @Override
    public void mostrarResumen(boolean mostrarDesglose) {
        if (mostrarDesglose) {
            double cargoDistancia = distancia * 0.50;
            System.out.println("\n--- RESUMEN COMPLETO (ENVIO NACIONAL) ---");
            System.out.println("Código: " + getCodigo());
            System.out.println("Destinatario: " + getDestinatario());
            System.out.println("Departamento Destino: " + departamento);
            System.out.println("Peso: " + getPeso() + "kg");
            System.out.println("Costo Base: Q" + String.format("%.2f", calcularCostoBase()));
            System.out.println("Cargo por Distancia ( " + distancia + "km): Q" + String.format("%.2f", cargoDistancia));
            System.out.println("Costo Final: Q" + String.format("%.2f", calcularCostoFinal()));
        } else {
            super.mostrarResumen(false);
        }
    }
}
