package com.bazar.proyecto.service.venta;

import com.bazar.proyecto.dto.ProductoVentaDTO;
import com.bazar.proyecto.dto.VentaClienteDTO;
import com.bazar.proyecto.dto.VentaMayorMontoDTO;
import com.bazar.proyecto.dto.VentaReporteMontoCantidadDTO;
import com.bazar.proyecto.model.DetalleVenta;
import com.bazar.proyecto.model.Producto;
import com.bazar.proyecto.model.Venta;
import com.bazar.proyecto.repository.IDetalleVentaRepository;
import com.bazar.proyecto.repository.IProductoRepository;
import com.bazar.proyecto.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository iVentaRepository;

    @Autowired
    private IDetalleVentaRepository iDetalleVentaRepository;

    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    public String guardarVenta(Venta venta) {

        //encuentro los productos y luego checkeo el stock
        List<DetalleVenta> listaDetallesVentas = this.encontrarProductos( venta );
        boolean ok = this.checkearStock(listaDetallesVentas);

        if (ok) {

            double total = this.calcularCosto( listaDetallesVentas );
            venta.setearTotal (total);
            this.setearIdDetalleVenta(venta);
            this.restarStock( listaDetallesVentas );
            iVentaRepository.save(venta);
            return "venta registrada";
        }else{
            return "error stock";
        }
    }

    private List<DetalleVenta> encontrarProductos ( Venta venta) {

        int cantidadALlevar  = 0;
        int cantidadALlevar2 = 0;
        int cantidadFinal = 0;
        List<Producto> listaProductos = iProductoRepository.findAll();

        List<DetalleVenta> listaDetalleVentas = venta.getListaDetalleVentas();
        List<DetalleVenta> listaCopia = new ArrayList<>();

        if (!listaProductos.isEmpty() && !listaDetalleVentas.isEmpty()) {
            for(DetalleVenta detalleVenta : listaDetalleVentas) {
                 for (Producto producto : listaProductos) {

                     //comparo si es el mismo producto que eligio comprar
                         if (producto.getCodigo_producto().equals(detalleVenta.getProducto().getCodigo_producto())) {
                             detalleVenta.setNombre_producto(producto.getNombre());
                             detalleVenta.setPrecio_unitario(producto.getCosto());
                             detalleVenta.setProducto(producto);
                             //lo guardo
                             listaCopia.add(detalleVenta);
                         }
                     }
             }
        }
        listaDetalleVentas.clear();
        listaDetalleVentas.addAll(listaCopia);



        System.out.println("Tamaño lista de detalles venta: " + venta.getListaDetalleVentas().size());
//        //remuevo duplicados
//        for (int i = 0; i < listaDetalleVentas.size(); i++) {
//
//            // si el producto asociado tiene el mismo nombre que el siguiente producto
//            // ->  cantidad1 + cantidad2 = cantidad a llevar final
//            if(productosEncontrados.get(i).getProducto().getNombre().
//                    equalsIgnoreCase(listaDetalleVentas.get( i + 1 ).getProducto().getNombre())) {
//
//                //agarro cantidad1 y cantidad2
//                cantidadALlevar  = productosEncontrados.get(i).getCantidadLlevar();
//                cantidadALlevar2 = productosEncontrados.get( i + 1 ).getCantidadLlevar();
//                cantidadFinal = cantidadALlevar + cantidadALlevar;
//                //seteo y remuevo el siguiente registro( el duplicado )
//                listaDetalleVentas.get(i).setCantidadLlevar(cantidadFinal);
//                listaDetalleVentas.remove(listaDetalleVentas.get( i + 1 ));
//            }
//        }
        return venta.getListaDetalleVentas();
    }

    private boolean checkearStock(List<DetalleVenta> listaDetalleVentas) {
        for(DetalleVenta detalleVenta : listaDetalleVentas) {
            //si la cantidad a llevar es mayor que la disponible en stock o si el stock del producto es 0 -> error
             if(detalleVenta.getCantidadLlevar() > detalleVenta.getProducto().getCantidad_dispo()
                      || detalleVenta.getProducto().getCantidad_dispo() < 1) {
                    return false;
                }
            }
        return true;
    }

    //(Bien)
    private double calcularCosto( List <DetalleVenta> listaDetalleVentas) {

        double subtotal = 0;
        double total = 0;


        if(!listaDetalleVentas.isEmpty()) {

            //calculo simple, subtotal = cantidad * precio
            for(DetalleVenta detalleVenta : listaDetalleVentas){
                subtotal = detalleVenta.getCantidadLlevar() * detalleVenta.getPrecio_unitario();
                detalleVenta.setSubtotal(subtotal);
                total += detalleVenta.getSubtotal();
            }
        }
        return total;
    }

    //(Bien)
    private void restarStock( List<DetalleVenta> listaDetalleVentas) {

         for(DetalleVenta  detalleVenta : listaDetalleVentas) {
             detalleVenta.getProducto().restarStock(detalleVenta.getCantidadLlevar());
             iProductoRepository.save(detalleVenta.getProducto());
         }
    }

    private void setearIdDetalleVenta(Venta venta) {
        for(DetalleVenta detalleVenta : venta.getListaDetalleVentas()){
            detalleVenta.setVenta(venta);
        }
    }

    @Override
    public List<VentaClienteDTO> traerListaVentas() {
        List<Venta> lista = this.iVentaRepository.findAll();
        return this.traerDTOS(lista);
    }

    @Override//hacer para que retorne un solo dto
    public VentaClienteDTO buscaVentaPorId(Long codigo_venta) {
        Venta venta = iVentaRepository.findById(codigo_venta).orElse(null);
        if (venta != null){
        return this.traerDTO(venta);
          }
        return null;
    }

    @Override
    public List<ProductoVentaDTO> traerProductosDeVenta(Long codigo_venta) {

        List<DetalleVenta> listaDetalleVentas = iDetalleVentaRepository.findAll();
        List<ProductoVentaDTO> listaDTO = new ArrayList<>();

        for(DetalleVenta dV : listaDetalleVentas){

            if( dV.getVenta().getCodigo_venta() == codigo_venta){
                ProductoVentaDTO productoVentaDTO = new ProductoVentaDTO();
                productoVentaDTO.setCodigo_venta(dV.getVenta().getCodigo_venta());
                productoVentaDTO.setNombre_producto(dV.getNombre_producto());
                productoVentaDTO.setCosto_producto(dV.getPrecio_unitario());
                productoVentaDTO.setCantidad_llevar_venta(dV.getCantidadLlevar());
                productoVentaDTO.setSubtotal_venta(dV.getSubtotal());
                // guardo el DTO en la lista de DTOs
                listaDTO.add(productoVentaDTO);
            }
        }
        return listaDTO;
    }

    @Override
    public VentaReporteMontoCantidadDTO montoCantidad(LocalDate fecha_venta) {

        List<Venta> listaVentas = iVentaRepository.findAll();
        VentaReporteMontoCantidadDTO montoCantidadDTO = new VentaReporteMontoCantidadDTO();
        int contador = 0;
        double acumulador = 0;

        for(Venta venta : listaVentas){
            if(venta.getFecha_venta().isEqual(fecha_venta)){
                contador++;
                acumulador += venta.getTotal();
            }
        }
        montoCantidadDTO.setCantidad_ventas(contador);
        montoCantidadDTO.setMonto_total(acumulador);
        return montoCantidadDTO;

    }

    @Override
    public VentaMayorMontoDTO mayorVenta() {

        List<Venta> listaVentas = iVentaRepository.findAll();
        VentaMayorMontoDTO dto = new VentaMayorMontoDTO();

        //seteo inicialmente, despues piso el monto del DTO
        double montoMasAlto = 0;
        dto.setMonto(montoMasAlto);

        for(Venta venta : listaVentas){
            if( venta.getTotal()> dto.getMonto() ){
                dto.setCodigo_venta(venta.getCodigo_venta());
                dto.setMonto(venta.getTotal());
                dto.setNombre_cliente(venta.getCliente().getNombre());
                dto.setApellido_cliente(venta.getCliente().getApellido());
                dto.setFecha_venta(venta.getFecha_venta());
            }
        }

        return dto;
    }

    @Override
    public String cancelarVenta(Long codigo_venta) {

        Venta venta = iVentaRepository.findById(codigo_venta).orElse(null);

        //agarro su lista de detalles (contiene productos )
        if (venta != null) {
                 // si la venta tiene "false" en "cancelada" entra
                if (venta.isCancelada() == false) {

                            List<DetalleVenta> listaDetalles = this.encontrarProductos(venta);
                            for (DetalleVenta dV : listaDetalles) {

                                //devuelvo el stock
                                int cantidadDevolver = dV.getCantidadLlevar();
                                dV.getProducto().agregarStock(cantidadDevolver);
                                dV.getVenta().setCancelada(true); // <- para evitar que vuelva a agregarle stock
                                iProductoRepository.save(dV.getProducto());
                            }
                             return "exito";
                }
                return "ya fue cancelada";
          }
        return "null";
    }

    public List<VentaClienteDTO> traerDTOS(List<Venta> listaVentas) {
        List<VentaClienteDTO> listaDTO = new ArrayList<>();

        for(Venta venta : listaVentas){
            VentaClienteDTO dto = new VentaClienteDTO();
            dto.setCodigo_venta(venta.getCodigo_venta());
            dto.setMonto(venta.getTotal());
            dto.setNombre_cliente(venta.getCliente().getNombre());
            dto.setApellido_cliente(venta.getCliente().getApellido());
            dto.setFecha_venta(venta.getFecha_venta());

            listaDTO.add(dto);
        }
        return listaDTO;
    }
    public VentaClienteDTO traerDTO(Venta venta) {
        VentaClienteDTO dto = new VentaClienteDTO();
            dto.setCodigo_venta(venta.getCodigo_venta());
            dto.setMonto(venta.getTotal());
            dto.setNombre_cliente(venta.getCliente().getNombre());
            dto.setApellido_cliente(venta.getCliente().getApellido());
            dto.setFecha_venta(venta.getFecha_venta());
        return dto;
    }
}
