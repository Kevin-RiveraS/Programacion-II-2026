import java.util.Scanner;

public class EnvioInternacional extends Envio {
    private String paisDestino;
    private final double CARGO_FIJO = 75.00;

    public EnvioInternacional(String codigo, String destinatario, double peso, String paisDestino){
        super(codigo, destinatario, peso);
        this.paisDestino = paisDestino;
    }

    public String getPaisDestino(){
        return paisDestino;
    }

    @Override
    public double calcularCostoFinal(){
        double costoBase = calcularCostoBase();
        double recargo = costoBase * 0.12;
        return costoBase + CARGO_FIJO + recargo;
    }

    @Override
    public void  mostrarResumen(boolean mostrarDesglose){
        if (mostrarDesglose) {
            double costoBase = calcularCostoBase();
            double recargo = costoBase * 0.12;
            System.out.println("\n--- RESUMEN COMPLETO (ENVÍO INTERNACIONAL) ---");
            System.out.println("Código: " + getCodigo());
            System.out.println("Destinatario: " + getDestinatario());
            System.out.println("País de Destino:" + paisDestino);
            System.out.println("Peso: " + getPeso() + "kg");
            System.out.println("Costo Base: Q" + String.format("%.2f", costoBase));
            System.out.println("Cargo fijo Internacional: Q" + String.format("%.2f", CARGO_FIJO));
            System.out.println("Recargo del 12%: Q" + String.format("%.2f", recargo));
            System.out.println("Costo Final: Q" + String.format("%.2f", calcularCostoFinal()));
        }
        else
        {
            super.mostrarResumen(false);
        }
    }
}
