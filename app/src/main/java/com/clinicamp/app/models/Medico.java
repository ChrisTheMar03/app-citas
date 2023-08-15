package com.clinicamp.app.models;

public class Medico {
    private Integer idMe;
    private String nombre;
    private String apellido;
    private String sexo;
    private Boolean activo;
    private String imagen;
    private Especialidad idEsp;

    public Medico(Integer idMe, String nombre, String apellido, String sexo, Boolean activo, String imagen, Especialidad idEsp) {
        this.idMe = idMe;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.activo = activo;
        this.imagen = imagen;
        this.idEsp = idEsp;
    }

    public Integer getIdMe() {
        return idMe;
    }

    public void setIdMe(Integer idMe) {
        this.idMe = idMe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Especialidad getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(Especialidad idEsp) {
        this.idEsp = idEsp;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "idMe=" + idMe +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", sexo='" + sexo + '\'' +
                ", activo=" + activo +
                ", imagen='" + imagen + '\'' +
                ", idEsp=" + idEsp +
                '}';
    }

    public Medico(Integer idMe) {
        this.idMe = idMe;
    }
}
