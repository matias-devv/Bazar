package com.bazar.proyecto.service.venta;

import com.bazar.proyecto.dto.ProductoVentaDTO;
import com.bazar.proyecto.dto.VentaClienteDTO;
import com.bazar.proyecto.dto.VentaMayorMontoDTO;
import com.bazar.proyecto.dto.VentaReporteMontoCantidadDTO;
import com.bazar.proyecto.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {


    //create
    public String guardarVenta(Venta venta);

    //get
    public List<VentaClienteDTO> traerListaVentas();
    public VentaClienteDTO buscaVentaPorId(Long codigo_venta);

               //-> productos de una determinada venta
    public List<ProductoVentaDTO> traerProductosDeVenta(Long codigo_venta);

              //->Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    public VentaReporteMontoCantidadDTO montoCantidad(LocalDate fecha_venta);

    public VentaMayorMontoDTO mayorVenta();

    //cancelar venta
    public String cancelarVenta(Long codigo_venta);
}
