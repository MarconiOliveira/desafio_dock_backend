package tech.dock.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.dock.desafio.model.Transacao;
import tech.dock.desafio.service.TransacaoService;

@RestController
@RequestMapping("/v1/transacoes")
public class TransacaoController {
	
	/*
	 * Não estou usando o MediaType.TEXT_HTML_VALUE(text/html) porque é 
	 * diferente do enunciado no desafio 
	 */
	private final String POST_CONTENT_TYPE = "text/html; charset=utf-8";
	
	@Autowired
	private TransacaoService service;

	@GetMapping
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok().body("Olá mundo");
	}
	
	@PostMapping(consumes= POST_CONTENT_TYPE, 
			     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transacao> create(@RequestBody String transactionString){
		System.out.println(transactionString);
		return ResponseEntity.badRequest().build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Transacao> findById(@PathVariable Integer id){
		if (id.intValue() <= 0) {
			//TODO criar padrão de retorno de erro
			return ResponseEntity.badRequest().build();
		}
		
		Transacao ret = this.service.findById(id);
		if (ret == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ret);
	}

	
}