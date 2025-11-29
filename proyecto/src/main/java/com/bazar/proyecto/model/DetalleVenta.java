package com.bazar.proyecto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_detalle_venta;
    private String nombre_producto;
    private int cantidadLlevar;
    private double precio_unitario;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name="codigo_venta",
                referencedColumnName="codigo_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name="codigo_producto",
                referencedColumnName="codigo_producto")
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id_detalle_venta, String nombre_producto, int cantidadLlevar, double precio_unitario, double subtotal, Venta venta, Producto producto) {
        this.id_detalle_venta = id_detalle_venta;
        this.nombre_producto = nombre_producto;
        this.cantidadLlevar = cantidadLlevar;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.venta = venta;
        this.producto = producto;
    }
}
