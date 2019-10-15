package com.gustavoferreira.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gustavoferreira.cursomc.domain.Cliente;
import com.gustavoferreira.cursomc.dto.ClienteDTO;
import com.gustavoferreira.cursomc.repositories.ClienteRepository;
import com.gustavoferreira.cursomc.services.exception.DataIntegrityException;
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
	
	/*public Cliente insert(Cliente obj) {
		obj.setId(null);
		return cliRepo.save(obj);
	}*/
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findId(obj.getId());
		updateData(newObj, obj);
		return cliRepo.save(newObj);
	}
	
	public void delete(Integer id) {
		findId(id);
		try {
			cliRepo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir este Cliente, pois há entidades relacionadas!");
		}
	}
	
	public List<Cliente> findAll(){
		return cliRepo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cliRepo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
