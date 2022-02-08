package tech.dock.desafio.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjetoNaoEncontradoException(){
        super("Objeto não encontrado!");
    }

	public ObjetoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjetoNaoEncontradoException(String message) {
		super(message);
	}

	public ObjetoNaoEncontradoException(Throwable cause) {
		super(cause);
	}
    
}