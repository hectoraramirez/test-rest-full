package com.example.cliente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cliente.entity.Cliente;
import com.example.cliente.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Cliente> findAll() {
		return clienteRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return clienteRepo.findById(id);
	}
	
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteRepo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findByUserOrEmail(String username, String email) {
		return clienteRepo.findByUserOrEmail(username, email);
	}

}
