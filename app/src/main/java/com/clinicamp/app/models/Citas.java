package com.clinicamp.app.models;

import java.util.Date;

public class Citas {

    private Integer idCitas;
    private Medico idMed;
    private Usuario idUser;
    private String fechaAtencion;
    private Boolean estado;

    public Citas(Integer idCitas, Medico idMed, Usuario idUser, String fechaAtencion, Boolean estado) {
        this.idCitas = idCitas;
        this.idMed = idMed;
        this.idUser = idUser;
        this.fechaAtencion = fechaAtencion;
        this.estado = estado;
    }

    public Integer getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public Medico getIdMed() {
        return idMed;
    }

    public void setIdMed(Medico idMed) {
        this.idMed = idMed;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "idCitas=" + idCitas +
                ", idMed=" + idMed +
                ", idUser=" + idUser +
                ", fechaAtencion=" + fechaAtencion +
                ", estado=" + estado +
                '}';
    }
}
