package tech.dock.desafio.dto;


import javax.validation.ValidationException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.dock.desafio.json.deserializable.IntToHexaStringDeserializer;
import tech.dock.desafio.json.serializable.IntToHexaStringSerializer;
import tech.dock.desafio.model.Terminal;
import tech.dock.desafio.validators.TransacaoDTOSchemaValidator;
import tech.dock.desafio.validators.TerminalStringValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalDTO {
	
	private Integer logic;
	private String serial;
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
	
	public TerminalDTO(String transactionString) throws ValidationException {
		
		TerminalStringValidator tsValidator = new TerminalStringValidator();
		tsValidator.validate(transactionString);
		
		String[] parts = transactionString.split(";");
		
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
		
		TransacaoDTOSchemaValidator sValidator = new TransacaoDTOSchemaValidator();
		sValidator.validate(this);

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
	
	public static void main(String[] args) {
		try {
			TerminalDTO dto = new TerminalDTO("44332211;123;PWWIN;0;2E4088B;4;8.00b3;0;16777216;PWWIN"); //F04A2E4088B
			System.out.println(dto.toString());
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Terminal toModel() {
		Terminal m = new Terminal();
		
		m.setId(this.logic);
		m.setSerial(this.serial);
		m.setModel(this.model);
		m.setSam(this.sam);
		m.setPtId(this.ptid);
		m.setPlat(this.plat);
		m.setVersion(this.version);
		m.setMxr(this.mxr);
		m.setMxf(this.mxf);
		m.setPverfm(this.pverfm);
	
		return m;
	}

}