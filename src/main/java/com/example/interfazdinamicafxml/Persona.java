package com.example.interfazdinamicafxml;


public class Persona {
    private String nombre;
    private String status;
    private String image;
    private String species;

    public Persona(String nombre, String status, String image) {
        this.nombre = nombre;
        this.image = image;
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {

        return nombre;
    }
}
