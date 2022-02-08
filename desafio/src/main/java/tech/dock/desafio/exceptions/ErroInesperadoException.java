package tech.dock.desafio.exceptions; 

public class ErroInesperadoException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ErroInesperadoException(){
        super();
    }

	public ErroInesperadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErroInesperadoException(String message) {
		super(message);
	}

	public ErroInesperadoException(Throwable cause) {
		super(cause);
	}

}