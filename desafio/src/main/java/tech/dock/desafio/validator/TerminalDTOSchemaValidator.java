package tech.dock.desafio.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import net.jimblackler.jsonschemafriend.GenerationException;
import net.jimblackler.jsonschemafriend.ListValidationException;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.ValidationError;
import net.jimblackler.jsonschemafriend.ValidationException;
import net.jimblackler.jsonschemafriend.Validator;
import tech.dock.desafio.dto.TerminalDTO;
import tech.dock.desafio.exceptions.ValidacaoException;

public class TerminalDTOSchemaValidator implements AppValidator<TerminalDTO> {

	@Override
	public void validate(TerminalDTO dto) throws ValidacaoException {

		try {
			Gson gson = new Gson();
			String json = gson.toJson(dto);
			
			SchemaStore schemaStore = new SchemaStore(); // Initialize a SchemaStore.
			// Load the schema.
			Schema schema = schemaStore
					.loadSchema(TerminalDTOSchemaValidator.class.getResource("/json/schema/terminal-schema.json"));

			Validator validator = new Validator();
			
			validator.validate(schema, new ObjectMapper().readValue(json, Map.class));
			
		}catch (ListValidationException e) {
			List<String> erros = new ArrayList<String>();
			for (ValidationError error : e.getErrors()) {
				erros.add(error.getMessage());
			}
			System.out.println(erros);
			throw new ValidacaoException("Erro ao validar o schema do terminal: " + erros.toString());
			
		} catch (GenerationException | JsonProcessingException  | ValidationException e) {
			throw new ValidacaoException(e.getMessage());
		}
	}

	@Override
	public boolean isValid(TerminalDTO t) {
		boolean ret = true;
		try {
			this.validate(t);
		}catch(ValidacaoException e) {
			ret = false;
		}
		return ret;
	}

}