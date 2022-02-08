package tech.dock.desafio.validator;

import tech.dock.desafio.exceptions.ValidacaoException;

public class TerminalStringValidator implements AppValidator<String>{
	
	private final String MESSAGE_TS_INVALIDO = "STRING_DE_TERMINAL_INVALIDA";
	private final String MESSAGE_TS_NULO = "A_STRING_DE_TERMINAL_NAO_PODE_SER_NULA";
	
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
	
	@Override
	public boolean isValid(String ts) {
		try {
			this.validate(ts);
			return true;
		}catch (ValidacaoException e) {
			return false;
		}
	}
	

	public void validate(String ts) {
		if (ts==null) {
			throw new ValidacaoException(MESSAGE_TS_NULO);
		}
		
		ts = ts + " ";
		String[] parts = ts.split(";");
		
		if (parts.length != 10) {
			throw new ValidacaoException(MESSAGE_TS_INVALIDO);
		}
		
		for (int x=0; x<parts.length; x++) {
			
			if (!PARTS_TYPE[x].isValid(parts[x])) {
				throw new ValidacaoException(MESSAGE_TS_INVALIDO);
			}
			
		}//for
		
	}

		
}
