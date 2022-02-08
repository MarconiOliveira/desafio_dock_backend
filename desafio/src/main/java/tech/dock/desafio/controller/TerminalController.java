package tech.dock.desafio.controller;

import javax.validation.Valid;

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
	
	@Autowired
	private TerminalService service;

	@GetMapping
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok().body("Olá mundo");
	}
	
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
	public ResponseEntity<TerminalDTO> update(@PathVariable Integer id, @Valid @RequestBody TerminalDTO dto){
		return ResponseEntity.ok(dto);		
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
	
	private void preSave(TerminalDTO dto) {
		TerminalDTOSchemaValidator sValidator = new TerminalDTOSchemaValidator();
		sValidator.validate(dto);
	}
	
}