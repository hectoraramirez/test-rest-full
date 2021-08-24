package com.example.cliente.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cliente.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query("select c from Cliente c where c.nombre_usuario = :username and c.correo_electronico = :email")
	public Optional<Cliente> findByUserOrEmail(@Param("username") String username, @Param("email") String email);

}
