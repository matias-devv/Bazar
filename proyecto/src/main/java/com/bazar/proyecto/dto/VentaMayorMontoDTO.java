package com.bazar.proyecto.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class VentaMayorMontoDTO {

    private int id;
    private Long codigo_venta;
    private double monto;
    private LocalDate fecha_venta;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaMayorMontoDTO() {
    }

    public VentaMayorMontoDTO(Long codigo_venta, double monto, LocalDate fecha_venta, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.monto = monto;
        this.fecha_venta = fecha_venta;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }

}
