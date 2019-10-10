package com.gustavoferreira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoferreira.cursomc.domain.Cliente;
import com.gustavoferreira.cursomc.repositories.ClienteRepository;
import com.gustavoferreira.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cliRepo;
	
	public Cliente findId(Integer id) {
		Optional<Cliente> obj = cliRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
}
