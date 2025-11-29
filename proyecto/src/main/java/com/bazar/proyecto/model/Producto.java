package com.bazar.proyecto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private double costo;
    private double cantidad_dispo;

    public Producto() {
    }

    public Producto(Long codigo_producto, String nombre, String marca, double costo,
                    double cantidad_dispo) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_dispo = cantidad_dispo;
    }

    public void restarStock(int cantidadALlevar){
        this.cantidad_dispo = this.cantidad_dispo - cantidadALlevar;
    }
    public void agregarStock(int cantidadAgregar){
        this.cantidad_dispo = this.cantidad_dispo + cantidadAgregar;
    }
}
