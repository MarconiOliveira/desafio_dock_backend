package tech.dock.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.dock.desafio.dto.TerminalDTO;
import tech.dock.desafio.exceptions.ValidacaoException;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.service.TerminalService;
import tech.dock.desafio.validator.TerminalDTOSchemaValidator;

@RestController
@RequestMapping("/v1/terminal")
public class TerminalController {
	
	/*
	 * Não estou usando o MediaType.TEXT_HTML_VALUE(text/html) porque é 
	 * diferente do enunciado no desafio 
	 */
	private final String POST_CONTENT_TYPE = MediaType.TEXT_HTML_VALUE;// "text/html; charset=utf-8";
	
	private final String MSG_ERRO_ID_PUT = "ID_NAO_CORRESPONDE_AO_CAMPO_LOGIC_DA_ENTIDADE";
	
	@Autowired
	private TerminalService service;

	
	@PostMapping(consumes= POST_CONTENT_TYPE, 
			     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TerminalDTO> create(@RequestBody String transactionString){
		TerminalDTO dto = null;
		dto = new TerminalDTO(transactionString);
		
		preSave(dto);
			
		this.service.insert(dto.toModel());

		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<TerminalDTO> update(@PathVariable(required = true) Integer id, @RequestBody TerminalDTO dto){
		this.preSave(dto);
		
		if (!id.equals(dto.getLogic())) {
			throw new ValidacaoException(MSG_ERRO_ID_PUT);
		}
		Terminal terminal = this.service.update(dto.toModel());
		
		return ResponseEntity.ok(TerminalDTO.build(terminal));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TerminalDTO> findById(@PathVariable Integer id){
		if (id.intValue() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		
		Terminal terminal = this.service.findById(id);
		if (terminal == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(TerminalDTO.build(terminal));
	}
	
	@GetMapping
	public ResponseEntity<List<TerminalDTO>> findAll(){
		List<Terminal> rs = this.service.findAll();
		
		List<TerminalDTO> ret = new ArrayList<TerminalDTO>();
		for (Terminal terminal : rs) {
			ret.add(TerminalDTO.build(terminal));
		}
		
		return ResponseEntity.ok().body(ret);
		
	}
	
	private void preSave(TerminalDTO dto) {
		TerminalDTOSchemaValidator sValidator = new TerminalDTOSchemaValidator();
		sValidator.validate(dto);
	}
	
}