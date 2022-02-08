package tech.dock.desafio.exceptions;

public class PermissaoNegadaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PermissaoNegadaException() {
		super();
	}

	public PermissaoNegadaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissaoNegadaException(String message) {
		super(message);
	}

	public PermissaoNegadaException(Throwable cause) {
		super(cause);
	}
	

}
