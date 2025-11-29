package com.bazar.proyecto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity @Getter @Setter
public class Venta {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private double total;
    private boolean cancelada;

    @ManyToOne
    @JoinColumn(name="id_cliente",
            referencedColumnName="id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> listaDetalleVentas;


    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, double total, boolean cancelada, Cliente cliente, List<DetalleVenta> listaDetalleVentas) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.cancelada = cancelada;
        this.cliente = cliente;
        this.listaDetalleVentas = listaDetalleVentas;
    }

    public void setearTotal(double total){
        this.total = total;
    }
}
