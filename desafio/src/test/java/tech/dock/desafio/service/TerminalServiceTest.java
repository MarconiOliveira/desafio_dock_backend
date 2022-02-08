package tech.dock.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tech.dock.desafio.constants.TerminalTestConstants;
import tech.dock.desafio.dto.TerminalDTO;
import tech.dock.desafio.exceptions.ObjetoExistenteException;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.repository.TerminalRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TerminalServiceTest {
	
	@MockBean
	private TerminalRepository repository;
	
	@InjectMocks
	private TerminalService service;

	@Test
	public void deveRetonarTerminal_QuandoInclusaoForBemSucedida() {
		TerminalDTO dto = new TerminalDTO(TerminalTestConstants.STRING_VALIDA);
		
		Terminal tIn = dto.toModel();
		
		when(this.repository.save(dto.toModel()))
			.thenReturn(tIn);
		
		Terminal tOut = service.insert(tIn);
		
		assertEquals(tIn, tOut);
	}

	@Test
	public void deveRetornarObjetoExistenteException_QuandoPassarTerminalExistente() {
		
		TerminalDTO dto = new TerminalDTO(TerminalTestConstants.STRING_VALIDA);
		
		Terminal tIn = dto.toModel();
		Integer id = tIn.getLogic();
		
		when(this.repository.existsById(id))
			.thenReturn(true);
		
		Assertions.assertThrows(ObjetoExistenteException.class, () -> service.insert(tIn));
		
	}

}