package com.bazar.proyecto.controller;

import com.bazar.proyecto.model.Producto;
import com.bazar.proyecto.service.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;

    //create
    @PostMapping("/productos/crear")
    public String createProducto(@RequestBody Producto producto){
        iProductoService.guardarProducto(producto);
        return "El producto se creo correctamente";
    }

    @PostMapping("/productos/crear/lista")
    public String createProducto(@RequestBody List<Producto> productos){
        iProductoService.guardarListaProductos(productos);
        return "Los productos se crearon correctamente";
    }
    //read
    @GetMapping("/productos")
    @ResponseBody
    public List<Producto> listarProductos(){
        return iProductoService.traerListaProductos();
    }

    @GetMapping("/productos/{codigo_producto}")
    @ResponseBody
    public Producto buscarProductoPorId(@PathVariable Long codigo_producto){
        return iProductoService.buscaProductoPorId(codigo_producto);
    }

    @GetMapping("/productos/falta_stock")
    @ResponseBody
    public List<Producto> faltaStock(){
        return iProductoService.faltaStock();
    }

    //update
    @PutMapping("/productos/editar")
    public String editarProducto(@RequestBody Producto producto){
        iProductoService.editarProducto(producto);
        return "El producto se edito correctamente";
    }
    //delete
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String eliminarProducto(@PathVariable Long codigo_producto){
        iProductoService.eliminarProducto(codigo_producto);
        return "El producto se elimino correctamente";
    }
}
