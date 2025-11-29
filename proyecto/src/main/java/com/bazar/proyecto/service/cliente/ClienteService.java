package com.bazar.proyecto.service.cliente;

import com.bazar.proyecto.model.Cliente;
import com.bazar.proyecto.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public void guardarCliente(Cliente cliente) {
        iClienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> traerListaClientes() {
        return iClienteRepository.findAll();
    }

    @Override
    public Cliente buscaClientePorId(Long id) {
        return iClienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        iClienteRepository.deleteById(id);
    }

    @Override
    public void editarCliente(Cliente cliente) {
        this.guardarCliente(cliente);
    }
}
