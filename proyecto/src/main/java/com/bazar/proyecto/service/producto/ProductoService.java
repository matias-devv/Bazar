package com.bazar.proyecto.service.producto;

import com.bazar.proyecto.model.Producto;
import com.bazar.proyecto.repository.IProductoRepository;
import com.bazar.proyecto.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;
    @Autowired
    private IVentaRepository iVentaRepository;

    @Override
    public void guardarProducto(Producto producto) {
        iProductoRepository.save(producto);
    }

    @Override
    public void guardarListaProductos(List<Producto> productos) {
        iProductoRepository.saveAll(productos);
    }

    @Override
    public List<Producto> traerListaProductos() {
        return iProductoRepository.findAll();
    }

    @Override
    public Producto buscaProductoPorId(Long id) {
        return iProductoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> faltaStock() {
        List<Producto> listaProductos = this.traerListaProductos();
        List<Producto> listaNueva = new ArrayList<>();

        for(Producto producto : listaProductos){
            if(producto.getCantidad_dispo() < 5){
                listaNueva.add(producto);
            }
        }
        return listaNueva;
    }

    @Override
    public void eliminarProducto(Long id) {
        iProductoRepository.deleteById(id);
    }

    @Override
    public void editarProducto(Producto producto) {
        this.guardarProducto(producto);
    }
}
