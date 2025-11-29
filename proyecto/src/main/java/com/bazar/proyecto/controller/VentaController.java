package com.bazar.proyecto.controller;

import com.bazar.proyecto.dto.ProductoVentaDTO;
import com.bazar.proyecto.dto.VentaClienteDTO;
import com.bazar.proyecto.dto.VentaMayorMontoDTO;
import com.bazar.proyecto.dto.VentaReporteMontoCantidadDTO;
import com.bazar.proyecto.model.Venta;
import com.bazar.proyecto.service.venta.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService iVentaService;

    //create
    @PostMapping("/ventas/crear")
    public String crearVenta(@RequestBody Venta venta) {
        String mensaje = iVentaService.guardarVenta(venta);

        if (mensaje.equalsIgnoreCase("venta registrada")) {
            return "La venta se creo correctamente";
        }
        else if(mensaje.equalsIgnoreCase("error stock")){
            return "No hay stock disponible para realizar esta venta";
        }
        return mensaje;
    }

    //read
    @GetMapping("/ventas")
    @ResponseBody
    public List<VentaClienteDTO> traerListaVentas(){
        return iVentaService.traerListaVentas();
    }

    @GetMapping("/ventas/traer/{codigo_venta}")
    ResponseEntity <VentaClienteDTO> traerVentaPorId(@PathVariable Long codigo_venta){
        VentaClienteDTO dto = iVentaService.buscaVentaPorId(codigo_venta);
        if(dto != null){
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

              //-> productos de una determinada venta
    @GetMapping("/ventas/productos/{codigo_venta}")
    @ResponseBody
    public List<ProductoVentaDTO> traerProductosDeVenta(@PathVariable Long codigo_venta){
        return iVentaService.traerProductosDeVenta(codigo_venta);
    }

              //-> sumatoria del monto y también cantidad total de ventas de un determinado día
    @GetMapping("/ventas/{fecha_venta}")
    @ResponseBody
    public VentaReporteMontoCantidadDTO montoCantidad(@PathVariable LocalDate fecha_venta){
        return iVentaService.montoCantidad(fecha_venta);
    }

              /* -> traigo el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
                 apellido del cliente de la venta con el monto más alto de todas*/
    @GetMapping("/ventas/mayor_venta")
    @ResponseBody
    public VentaMayorMontoDTO mayorVenta(){
        return iVentaService.mayorVenta();
    }

    @PutMapping("/ventas/cancelar/{codigo_venta}")
    public String editarVenta(@PathVariable Long codigo_venta){
        String mensaje = iVentaService.cancelarVenta(codigo_venta);

        if (mensaje.equalsIgnoreCase("exito")) {
            return "La venta se cancelo correctamente";
        }
        else{
            if(mensaje.equalsIgnoreCase("null")){
                return "La venta no esta registrada en nuestro sistema";
            }
            if(mensaje.equalsIgnoreCase("ya fue cancelada")){
                return "La venta ya fue cancelada anteriormente";
            }
        }

        return mensaje;
    }
}
