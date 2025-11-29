package com.bazar.proyecto.service.producto;

import com.bazar.proyecto.model.Producto;

import java.util.List;

public interface IProductoService {

    //create
    public void guardarProducto(Producto producto);
    public void guardarListaProductos(List<Producto> productos);
    //read
    public List<Producto> traerListaProductos();
    public Producto buscaProductoPorId(Long id);
    public List<Producto> faltaStock();
    //update
    public void editarProducto(Producto producto);
    //delete
    public void eliminarProducto(Long id);
}
