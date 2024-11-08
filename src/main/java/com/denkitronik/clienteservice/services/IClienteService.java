package com.denkitronik.clienteservice.services;


import com.denkitronik.clienteservice.entities.Cliente;
import com.denkitronik.clienteservice.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
	
	List<Cliente> findAll();

	Page<Cliente> findAll(Pageable pageable);

	Cliente save(Cliente cliente);

	Cliente findById(Long id);

	void delete(Long id);

	List<Region> findAllRegiones();
}
