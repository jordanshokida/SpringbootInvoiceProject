package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.entity.Factura;
import com.coderhouse.segundaentregaproyectofinal.exception.DbException;
import com.coderhouse.segundaentregaproyectofinal.exception.ResourceNotFoundException;
import com.coderhouse.segundaentregaproyectofinal.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;


    public Cliente crearCliente(Cliente cliente) {


        return clienteRepository.save(cliente);

    }

    public Cliente buscarOCrearCliente(Cliente cliente) {

        Optional<Cliente> miCliente = clienteRepository.findById(cliente.getId());

        if (miCliente.isPresent()) {

            return miCliente.get();

        } else {

            return this.crearCliente(cliente);
        }

    }

    public void modificarTelefonoCliente(Long id, String tel) {

        Cliente clienteModificado = obtenerClientePorId(id);

        if (Pattern.matches("^[0-9]{2,3}[0-9]{4}[0-9]{4}$", tel)) {
            clienteModificado.setTelefono(tel);
            log.info("Se ha modificado el telefono de " + clienteModificado.getNombre().toUpperCase() + ", "
                    + clienteModificado.getApellido());
            clienteRepository.save(clienteModificado);
        } else {

            throw new DbException(
                    "El formato de teléfono no es valido. No debe contener espacios ni carácteres especiales.");
        }

    }

    public void borrarCliente(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id brindado no puede ser 0 ni negativo");
        }
        try {
            clienteRepository.deleteById(id);
        } catch (RuntimeException exception) {
            throw new DbException("No existe el cliente con el id " + id);
        }
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            log.info("El cliente fue eliminado exitosamente, su id es" + id);
        } else {
            log.info("No se pudo eliminar el cliente con el id" + id);
        }
    }

    public Cliente obtenerClientePorId(Long id)  throws ResourceNotFoundException{

        if (id <= 0) {
            throw new IllegalArgumentException("El id pedido no puede ser 0 ni negativo");
        }

        this.clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Cliente con el ID " + id + " no existe"));
        return this.clienteRepository.getById(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorNombre(String nombre) {

        return clienteRepository.findByNombre(nombre);
    }


    public List<Factura> obtenerFacturasPorIdCliente(Long id) {

        Cliente cliente = obtenerClientePorId(id);

        return cliente.getFacturas();
    }

}


