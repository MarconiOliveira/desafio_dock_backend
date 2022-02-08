package tech.dock.desafio.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tech.dock.desafio.constants.TerminalTestConstants;
import tech.dock.desafio.exceptions.ValidacaoException;

public class TerminalStringValidatorTest {
	
	@Test
	public void naoDeveGerarExcecao_QuandoStringForValida() {
		
		TerminalStringValidator validator = new TerminalStringValidator();
		
		assertDoesNotThrow(()->  validator.validate(TerminalTestConstants.STRING_VALIDA));
		
		Assertions.assertEquals(true, validator.isValid(TerminalTestConstants.STRING_VALIDA));

	}
	
	@Test
	public void deveRetornarTrue_QuantoStringForValida() {
		TerminalStringValidator validator = new TerminalStringValidator();
		Assertions.assertEquals(true, validator.isValid(TerminalTestConstants.STRING_VALIDA));
	}
	
	@Test
	public void deveRetornarFalse_QuantoStringForInvalida() {
		TerminalStringValidator validator = new TerminalStringValidator();
		Assertions.assertEquals(false, validator.isValid(TerminalTestConstants.STRING_INVALIDA_1));
	}
	
	@Test
	public void deveGerarExcecao_QuandoStringForInvalida() {
		
		TerminalStringValidator validator = new TerminalStringValidator();
		
		assertThrows(ValidacaoException.class, ()-> validator.validate(TerminalTestConstants.STRING_INVALIDA_1));
		
		assertThrows(ValidacaoException.class, ()-> validator.validate(TerminalTestConstants.STRING_INVALIDA_2));
		
		assertThrows(ValidacaoException.class, ()-> validator.validate(TerminalTestConstants.STRING_INVALIDA_3));
		
		assertThrows(ValidacaoException.class, ()-> validator.validate(TerminalTestConstants.STRING_INVALIDA_4));
		
	}

}