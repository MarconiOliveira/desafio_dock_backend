package tech.dock.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dock.desafio.exceptions.ObjetoExistenteException;
import tech.dock.desafio.exceptions.ObjetoNaoEncontradoException;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.repository.TerminalRepository;

@Service
public class TerminalService {
	
	private final String MSG_TERMINAL_JA_EXISTE = "TERMINAL_JA_CADASTRADO";
	private final String MSG_TERMINAL_NAO_ENCONTRADO = "TERMINAL_NAO_ENCONTRADO";
	
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
	
	public List<Terminal> findAll(){
		return this.repository.findAll();
	}
	
	public Terminal update(Terminal terminal) {
		if (!this.repository.existsById(terminal.getLogic())) {
			throw new ObjetoNaoEncontradoException(MSG_TERMINAL_NAO_ENCONTRADO);
		}
		return this.repository.save(terminal);
	}
	
}