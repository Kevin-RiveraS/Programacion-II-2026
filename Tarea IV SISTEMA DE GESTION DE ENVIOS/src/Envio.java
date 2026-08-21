import java.util.Scanner;

public class Envio {

    private String codigo;
    private String destinatario;
    private double peso;

    public Envio(String codigo, String destinatario, double peso) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        }
    }


    public double calcularCostoBase() {
        double costoBase = this.peso * 10.00;
        return costoBase;
    }

    public double calcularCostoFinal() {
        double costoFinal = calcularCostoBase();
        return costoFinal;
    }


    public void mostrarResumen() {
        System.out.println("--- RESUMEN DEL ENVIO ---");
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Destinatario: " + this.destinatario);
        System.out.println("Costo Final: Q" + calcularCostoFinal());
    }


    public void mostrarResumen(boolean desgloseCompleto) {
        if (desgloseCompleto == true) {
            System.out.println("--- DESGLOSE COMPLETO ---");
            System.out.println("Codigo: " + this.codigo);
            System.out.println("Destinatario: " + this.destinatario);
            System.out.println("Peso del paquete: " + this.peso + " kg");
            System.out.println("Costo base: Q" + calcularCostoBase());
            System.out.println("Costo final: Q" + calcularCostoFinal());
        } else {
            mostrarResumen();
        }
    }
}

