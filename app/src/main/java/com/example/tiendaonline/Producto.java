package com.example.tiendaonline;

public class Producto {
    public String id;
    public String nombre;
    public String descripcion;
    public double precio;

    public Producto(){

    }

    public Producto(String id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}
