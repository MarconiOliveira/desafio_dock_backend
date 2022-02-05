package tech.dock.desafio.json.deserializable;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class IntToHexaStringDeserializer extends JsonDeserializer<Integer> {

	@Override
	public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	String txt = jp.getText();
    	Integer ret = null;
    	if (txt != null && !txt.equals("")) {
    		ret = Integer.parseInt(txt.trim(), 16);
    	}
    	return ret;
	}
    
    
}