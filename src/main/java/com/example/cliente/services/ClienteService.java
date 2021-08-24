package com.example.cliente.services;

import java.util.Optional;

import com.example.cliente.entity.Cliente;

public interface ClienteService {
	
	public Iterable<Cliente> findAll();
	
	public Optional<Cliente> findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void deleteById(Long id);
	
	public Optional<Cliente> findByUserOrEmail(String username, String email);

}
