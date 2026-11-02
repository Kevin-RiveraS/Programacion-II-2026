package com.ejemplo.restaurante.dto;

public class MensajeRespuestaDTO {
    private String mensaje;
    private Object data;

    public MensajeRespuestaDTO(String mensaje, Object data) {
        this.mensaje = mensaje;
        this.data = data;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
