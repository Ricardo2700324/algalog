package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaModelDTO;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {
	
	@Autowired
	private RegistroOcorrenciaService registroOcorrenciaService;
	
	@Autowired
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@Autowired
	private  BuscaEntregaService buscaEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModelDTO registrar(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
	 
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(id, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public List<OcorrenciaModelDTO> listar(@PathVariable Long id){
		Entrega entrega = buscaEntregaService.buscar(id);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	} 
}
