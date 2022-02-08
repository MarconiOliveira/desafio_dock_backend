package tech.dock.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dock.desafio.exceptions.ObjetoExistenteException;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.repository.TerminalRepository;

@Service
public class TerminalService {
	
	private final String MSG_TERMINAL_JA_EXISTE = "TERMINAL_JA_CADASTRADO"; 
	
	@Autowired
	private TerminalRepository repository;
	
	public Terminal insert(Terminal terminal) {
		if (this.repository.existsById(terminal.getLogic())) {
			throw new ObjetoExistenteException(MSG_TERMINAL_JA_EXISTE);
		}
		return this.repository.save(terminal);
	}
	
	public Terminal findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}
	
}