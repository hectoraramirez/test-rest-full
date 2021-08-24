package com.example.cliente.controllers;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.entity.Cliente;
import com.example.cliente.services.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService serviceCliente;
	
	@GetMapping("/NutriNET/Cliente")
	public ResponseEntity<?> getAll() {
		
		return ResponseEntity.ok().body(serviceCliente.findAll());
	}
	
	@GetMapping("/NutriNET/Cliente/{id}")
	public ResponseEntity<?> getClient(@PathVariable Long id) {
		return ResponseEntity.ok().body(serviceCliente.findById(id));
	}
	
	@PostMapping("/NutriNET/Cliente")
	public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
		
		Optional<Cliente> clienteFind = serviceCliente.findByUserOrEmail(cliente.getNombre_usuario(), cliente.getCorreo_electronico());
		
		if (clienteFind.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(clienteFind);
		} else {
			Cliente clienteDB = serviceCliente.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteDB);
		}
		
		
	}
	
	@PutMapping("/NutriNET/Cliente/{id}")
	public ResponseEntity<?> updateClient(@RequestBody Cliente cliente, @PathVariable Long id){
		
		Optional<Cliente> clienteFind = serviceCliente.findById(id);
		
		if (clienteFind.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente clientDB = clienteFind.get();
		
		clientDB.setApellidos(cliente.getApellidos());
		clientDB.setContraseña(cliente.getContraseña());
		clientDB.setNombre(cliente.getNombre());
		clientDB.setEdad(cliente.getEdad());
		clientDB.setEstatura(cliente.getEstatura());
		clientDB.setPeso(cliente.getPeso());
		clientDB.setImc(cliente.getImc());
		clientDB.setGeb(cliente.getGeb());
		clientDB.setEta(cliente.getEta());
		clientDB.setUpdateAt(new Date());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceCliente.save(clientDB));
	}
	
}
