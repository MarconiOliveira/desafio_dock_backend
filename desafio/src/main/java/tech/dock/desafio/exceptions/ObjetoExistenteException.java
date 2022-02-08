package tech.dock.desafio.exceptions;

public class ObjetoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoExistenteException() {
		super();
	}

	public ObjetoExistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjetoExistenteException(String message) {
		super(message);
	}

	public ObjetoExistenteException(Throwable cause) {
		super(cause);
	}
	
}