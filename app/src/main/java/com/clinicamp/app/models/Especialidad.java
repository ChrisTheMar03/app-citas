package com.clinicamp.app.models;

public class Especialidad {

    private Integer idEsp;
    private String especialidad;
    private String descripcion;
    private String imagen;
    private Boolean actibo;

    public Especialidad(Integer idEsp, String especialidad, String descripcion, String imagen, Boolean actibo) {
        this.idEsp = idEsp;
        this.especialidad = especialidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.actibo = actibo;
    }

    public Integer getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(Integer idEsp) {
        this.idEsp = idEsp;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getActibo() {
        return actibo;
    }

    public void setActibo(Boolean actibo) {
        this.actibo = actibo;
    }
}
