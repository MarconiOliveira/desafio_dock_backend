package tech.dock.desafio.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tech.dock.desafio.constants.TerminalTestConstants;
import tech.dock.desafio.dto.TerminalDTO;

public class TerminalDTOSchemaValidatorTest {
	
	
	
	@Test
	public void deveRetornarTrue_QuandoDtoForValido() {
		
		TerminalDTO dto = new TerminalDTO(TerminalTestConstants.STRING_VALIDA);
		
		TerminalDTOSchemaValidator validator = new TerminalDTOSchemaValidator();
		
		assertEquals(true, validator.isValid(dto));
	}
	
	@Test
	public void deveRetornarFalse_QuandoDtoForInvalido() {
		TerminalDTO dto = new TerminalDTO(TerminalTestConstants.STRING_ESQUEMA_INVALIDA);
		
		TerminalDTOSchemaValidator validator = new TerminalDTOSchemaValidator();
		
		assertEquals(false, validator.isValid(dto));
	}

}