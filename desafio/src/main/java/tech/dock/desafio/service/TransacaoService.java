package tech.dock.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dock.desafio.model.Transacao;
import tech.dock.desafio.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repository;
	
	public Transacao findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}
	
	

}