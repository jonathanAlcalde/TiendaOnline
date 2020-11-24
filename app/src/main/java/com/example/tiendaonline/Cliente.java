package com.example.tiendaonline;

import java.util.HashMap;

public class Cliente {
    public String id;
    public String codigo;
    public String nombre;
    public boolean premium;
    public HashMap<String, Boolean> carrito;

    public Cliente(){

    }

    public Cliente(String id, String codigo, String nombre){
        this.id = id;
        this.codigo=codigo;
        this.nombre=nombre;
        this.premium=false;
        this.carrito=new HashMap<>();

    }
}
