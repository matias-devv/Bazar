package com.bazar.proyecto.controller;

import com.bazar.proyecto.model.Cliente;
import com.bazar.proyecto.service.cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    //create
    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente cliente) {
        iClienteService.guardarCliente(cliente);
        return "El cliente se guardo correctamente";
    }
    //read
    @GetMapping("/clientes")
    @ResponseBody
    public List<Cliente> traerClientes() {
        return iClienteService.traerListaClientes();
    }
    @GetMapping("/clientes/{id_cliente}")
    @ResponseBody
    public Cliente buscaClientePorId(@PathVariable Long id_cliente) {
        return iClienteService.buscaClientePorId(id_cliente);
    }

    //update
    @PutMapping("/clientes/editar")
    public String editarCliente(@RequestBody Cliente cliente) {
        iClienteService.editarCliente(cliente);
        return "El cliente se edito correctamente";
    }

    //delete
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente){
        iClienteService.eliminarCliente(id_cliente);
        return "El cliente se elimino correctamente";
    }

}
