package com.bazar.proyecto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaReporteMontoCantidadDTO {

    int id;
    double monto_total;;
    int cantidad_ventas;

    public VentaReporteMontoCantidadDTO() {
    }

    public VentaReporteMontoCantidadDTO(int id, double monto_total, int cantidad_ventas) {
        this.id = id;
        this.monto_total = monto_total;
        this.cantidad_ventas = cantidad_ventas;
    }
}
