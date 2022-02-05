package tech.dock.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.repository.TerminalRepository;

@Service
public class TerminalService {
	
	@Autowired
	private TerminalRepository repository;
	
	public Terminal findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}
	
	

}