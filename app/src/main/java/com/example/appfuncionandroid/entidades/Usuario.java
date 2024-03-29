package com.example.appfuncionandroid.entidades;

public class Usuario {

    private Integer id;
    private String nombre;
    private String telefono;

    public Usuario(Integer id, String nombre, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Integer gatId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
