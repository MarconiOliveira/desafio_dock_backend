package tech.dock.desafio.dto;


import javax.validation.ValidationException; 
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.dock.desafio.json.deserializable.IntToHexaStringDeserializer;
import tech.dock.desafio.json.serializable.IntToHexaStringSerializer;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.validator.TerminalStringValidator;

@Data
@NoArgsConstructor
public class TerminalDTO {
	
	private Integer logic;
	private String serial;
	
	@NotBlank
	private String model;
	private Integer sam;
	
	@JsonSerialize(using = IntToHexaStringSerializer.class)
	@JsonDeserialize(using = IntToHexaStringDeserializer.class)
	private Integer ptid;
	
	private Integer plat;
	private String version;
	private Integer mxr;
	private Integer mxf;
	
	@JsonProperty("PVERFM")
	private String pverfm;
	
	public TerminalDTO(String terminalString) throws ValidationException {
		
		TerminalStringValidator tsValidator = new TerminalStringValidator();
		tsValidator.validate(terminalString);
		
		this.parse(terminalString);	
		
	}
	
	private void parse(String terminalString) {
		
		//Evitando que a última parte seja nula
		terminalString = terminalString + " "; 
		String[] parts = terminalString.split(";");
		
		
		this.logic = this.parseInt(parts[0]);
		this.serial = this.parseString(parts[1]);
		this.model = this.parseString(parts[2]);
		this.sam = this.parseInt(parts[3]);
		this.ptid = this.parseIntHexa(parts[4]);
		this.plat = this.parseInt(parts[5]);
		this.version = this.parseString(parts[6]);
		this.mxr = this.parseInt(parts[7]);
		this.mxf = this.parseInt(parts[8]);
		this.pverfm = this.parseString(parts[9]);
	}
	
	private Integer parseInt(String part) {
		return part != null && !part.trim().equals("") ? Integer.parseInt(part) : null;
	}
	
	private Integer parseIntHexa(String part) {
		return part != null && !part.trim().equals("") ? Integer.parseInt(part, 16) : null;
	}
	
	private String parseString(String part) {
		String ret = null;
		if (part != null && !part.trim().equals("")) {
			ret = part.trim();
		}
		return ret;
	}
	
	public Terminal toModel() {
		ModelMapper mm = new ModelMapper();
		Terminal model = mm.map(this, Terminal.class);
		return model;
	}
	
	public static TerminalDTO build(Terminal model) {
		ModelMapper mm = new ModelMapper();
		TerminalDTO dto = mm.map(model, TerminalDTO.class);
		
		return dto;
	}

}