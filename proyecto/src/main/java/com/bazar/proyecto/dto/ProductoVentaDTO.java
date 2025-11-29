package com.bazar.proyecto.dto;

import com.bazar.proyecto.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductoVentaDTO {

    private int id;
    private Long codigo_venta;
    private String nombre_producto;
    private double costo_producto;
    private int cantidad_llevar_venta;
    private double subtotal_venta;

    public ProductoVentaDTO() {
    }

    public ProductoVentaDTO(int id, Long codigo_venta) {
        this.id = id;
        this.codigo_venta = codigo_venta;
    }

}
