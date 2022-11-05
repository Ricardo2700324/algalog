package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModelDTO;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

@Component
public class EntregaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public EntregaModelDTO toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModelDTO.class);
	}
	
	public List<EntregaModelDTO> toCollectionModel(List<Entrega> entregas){
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
