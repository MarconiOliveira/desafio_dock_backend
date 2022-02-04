package tech.dock.desafio;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import io.restassured.http.ContentType;
import tech.dock.desafio.controller.TransacaoController;
import tech.dock.desafio.model.Transacao;
import tech.dock.desafio.service.TransacaoService;

@WebMvcTest
public class TransacaoControllerTest {
	
	private final String PREFIXO_VERSAO = "/v1";
	private final String RESOURCE = "/transacoes";
	private final String URL_RESOURCE = PREFIXO_VERSAO + RESOURCE;
	
	@Autowired
	private TransacaoController controller;
	
	@MockBean
	private TransacaoService transacaoService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.controller);
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoPassarBodyErradoNoPost() {
		
		given()
			.contentType(ContentType.HTML)
			.body("ASDFG")
		.when()
			.post(URL_RESOURCE)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	
	@Test
	public void deveRetornarSucesso_QuandoProcurarPorId() {
		
		when(this.transacaoService.findById(1))
			.thenReturn(new Transacao());
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get(URL_RESOURCE + "/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoProcurarPorId() {
		
		when(this.transacaoService.findById(3))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get(URL_RESOURCE + "/{id}", 3)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoProcurarPorIdInvalido() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get(URL_RESOURCE + "/{id}", -1)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.transacaoService, never()).findById(-1);
		
	}

}