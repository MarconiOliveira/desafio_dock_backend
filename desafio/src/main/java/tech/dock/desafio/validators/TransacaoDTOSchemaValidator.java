package tech.dock.desafio.validators;

import java.util.Map;

import javax.validation.ValidationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;
import tech.dock.desafio.dto.TerminalDTO;

public class TransacaoDTOSchemaValidator implements AppValidator<TerminalDTO> {
	

	@Override
	public void validate(TerminalDTO dto) throws ValidationException {

		try {
			Gson gson = new Gson();
			String json = gson.toJson(dto);
			
			SchemaStore schemaStore = new SchemaStore(); // Initialize a SchemaStore.
			// Load the schema.
			Schema schema = schemaStore
					.loadSchema(TransacaoDTOSchemaValidator.class.getResource("/json/schema/transacao-schema.json"));

			Validator validator = new Validator();

			// Will not throw an exception.
			validator.validate(schema, new ObjectMapper().readValue(json, Map.class));

		} catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
	}

}