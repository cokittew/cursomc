package com.gustavoferreira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoferreira.cursomc.domain.Cliente;
import com.gustavoferreira.cursomc.domain.Pedido;
import com.gustavoferreira.cursomc.repositories.PedidoRepository;
import com.gustavoferreira.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pediRepo;

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = pediRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
