package com.bazar.proyecto.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class VentaClienteDTO {

    private Long codigo_venta;
    private double monto;
    private LocalDate fecha_venta;
    private int cantidadProductos;
    private int cantidadVentas;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaClienteDTO() {
    }

    public VentaClienteDTO(Long codigo_venta, double monto, LocalDate fecha_venta, int cantidadProductos, int cantidadVentas, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.monto = monto;
        this.fecha_venta = fecha_venta;
        this.cantidadProductos = cantidadProductos;
        this.cantidadVentas = cantidadVentas;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }

}
