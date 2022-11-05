package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
		
//		var cliente1 = new Cliente();
//		cliente1.setId(1L);
//		cliente1.setNome("João");
//		cliente1.setEmail("joaodascoves@gmail.com");
//		cliente1.setTelefone("99999129912");
//		
//		var cliente2 = new Cliente();
//		cliente2.setId(2L);
//		cliente2.setNome("Maria");
//		cliente2.setEmail("mariadasilva@gmail.com");
//		cliente2.setTelefone("9999446712");
//		
//		
//		
//		return Arrays.asList(cliente1, cliente2);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {

		return clienteRepository.findById(id)
		//		.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		
//		Optional<Cliente> cliente = clienteRepository.findById(id);
//
//		if (cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente);
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id,@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		//cliente = clienteRepository.save(cliente);
		catalogoClienteService.salvar(cliente); 
		
		 return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		//clienteRepository.deleteById(id);
		catalogoClienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}

}
