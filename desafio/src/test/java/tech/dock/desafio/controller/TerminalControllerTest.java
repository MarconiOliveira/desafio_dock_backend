package tech.dock.desafio.controller;

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
import tech.dock.desafio.constants.TerminalTestConstants;
import tech.dock.desafio.dto.TerminalDTO;
import tech.dock.desafio.exceptions.AppExceptionHandler;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.service.TerminalService;

@WebMvcTest
public class TerminalControllerTest {
	
	private final String PREFIXO_VERSAO = "/v1";
	private final String RESOURCE = "/terminal";
	private final String URL_RESOURCE = PREFIXO_VERSAO + RESOURCE;
	
	@Autowired
	private TerminalController controller;
	
	@Autowired
	private AppExceptionHandler exceptionHandler;
	
	@MockBean
	private TerminalService terminalService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.controller, this.exceptionHandler);
	}
		
	@Test
	public void deveRetornarOK_QuandoStringTerminalForValida() {
		
		TerminalDTO dto = new TerminalDTO(TerminalTestConstants.STRING_VALIDA);
		Terminal terminal = dto.toModel();
		
		when(this.terminalService.insert(terminal))
			.thenReturn(terminal);
		
		given()
			.contentType(ContentType.HTML)
			.body(TerminalTestConstants.STRING_VALIDA)
		.when()
			.post(URL_RESOURCE)
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoPassarBodyErradoNoPost() {
		
		given()
			.contentType(ContentType.HTML)
			.body(TerminalTestConstants.STRING_INVALIDA_3)
		.when()
			.post(URL_RESOURCE)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	
	@Test
	public void deveRetornarSucesso_QuandoProcurarPorId() {
		
		when(this.terminalService.findById(1))
			.thenReturn(new Terminal());
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get(URL_RESOURCE + "/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoProcurarPorId() {
		
		when(this.terminalService.findById(3))
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
		
		verify(this.terminalService, never()).findById(-1);
		
	}

}