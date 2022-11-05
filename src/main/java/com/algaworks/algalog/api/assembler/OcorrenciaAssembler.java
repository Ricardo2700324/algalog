package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.OcorrenciaModelDTO;
import com.algaworks.algalog.domain.model.Ocorrencia;

@Component
public class OcorrenciaAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaModelDTO toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModelDTO.class);
	}
	
	public List<OcorrenciaModelDTO> toCollectionModel(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	
}
