package tech.dock.desafio.validator;

import tech.dock.desafio.exceptions.ValidacaoException;

public interface AppValidator<T> {

	public void validate(T t) throws ValidacaoException;
	
	public boolean isValid(T t);
	
}