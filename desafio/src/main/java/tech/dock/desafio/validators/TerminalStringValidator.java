package tech.dock.desafio.validators;

import javax.validation.ValidationException;

public class TerminalStringValidator implements AppValidator<String>{
	
	public enum TsTypes {
		STRING {
			public boolean isValid(String test) {
				//validar apenas os inteiros, as strings
				//serao validadas no json-schemma
				return true;
			}
		}, 
		INTEGER {
			public boolean isValid(String test) {
				boolean ret = true;
				if (test != null && !test.trim().equals("")) {
					try {
						Integer.parseInt(test.trim());
					}catch (Exception e) {
						ret = false;
					}
				}//if
				return ret;
			}
		}, 
		INTEGER_HEXA {
			public boolean isValid(String test) {
				boolean ret = true;
				if (test != null && !test.trim().equals("")) {
					try {
						Integer.parseInt(test.trim(), 16);
					}catch (Exception e) {
						ret = false;
					}
				}//if
				return ret;
			}
		};
		
		public abstract boolean isValid(String test);
	};
	
	private final String MESSAGE_TS_INVALIDO = "String de terminal inválida";
	
	
	private final TsTypes[] PARTS_TYPE = new TsTypes[] {
		TsTypes.INTEGER,	//logic
		TsTypes.STRING,		//serial
		TsTypes.STRING,		//model
		TsTypes.INTEGER,	//sam
		TsTypes.INTEGER_HEXA,//ptid
		TsTypes.INTEGER,	//plat
		TsTypes.STRING,		//version
		TsTypes.INTEGER,	//mxr
		TsTypes.INTEGER,	//mxf
		TsTypes.STRING		//PVERFM
	};
	

	public void validate(String ts) throws ValidationException {
		if (ts==null) {
			throw new ValidationException("A string de terminal não pode ser nula");
		}
		
		String[] parts = ts.split(";");
		if (parts.length != 10) {
			throw new ValidationException(MESSAGE_TS_INVALIDO);
		}
		
		//old school
		for (int x=0; x<parts.length; x++) {
			
			if (!PARTS_TYPE[x].isValid(parts[x])) {
				throw new ValidationException(MESSAGE_TS_INVALIDO);
			}
			
		}//for
		
	}
		
}
