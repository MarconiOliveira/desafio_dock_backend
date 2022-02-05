package tech.dock.desafio.json.serializable;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IntToHexaStringSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer tmpInt, 
                          JsonGenerator jsonGenerator, 
                          SerializerProvider serializerProvider) 
                          throws IOException, JsonProcessingException {
    	String ret = null;
    	
    	if (tmpInt != null) {
    		ret = Integer.toString(tmpInt, 16).toUpperCase();
    	}
        jsonGenerator.writeObject(ret);
    }
    
    
}