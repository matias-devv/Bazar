package com.bazar.proyecto.service.cliente;

import com.bazar.proyecto.model.Cliente;

import java.util.List;

public interface IClienteService {

    //create
    public void guardarCliente(Cliente cliente);

    //get
    public List<Cliente> traerListaClientes();
    public Cliente buscaClientePorId(Long id);

    //delete
    public void eliminarCliente(Long id);

    //edit
    public void editarCliente(Cliente cliente);
}
