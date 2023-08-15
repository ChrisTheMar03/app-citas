package com.clinicamp.app.models;

public class MessageError{

    private String mensaje;
    private Boolean valor;
    private String httpStatus;

    public MessageError(String mensaje, Boolean valor, String httpStatus) {
        this.mensaje = mensaje;
        this.valor = valor;
        this.httpStatus = httpStatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getValor() {
        return valor;
    }

    public void setValor(Boolean valor) {
        this.valor = valor;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}
