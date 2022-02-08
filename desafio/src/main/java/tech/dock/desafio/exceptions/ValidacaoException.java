package tech.dock.desafio.exceptions;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	
	public ValidacaoException(String message) {
		super(message);
	}

	public ValidacaoException(Throwable cause) {
		super(cause);
	}


	
}