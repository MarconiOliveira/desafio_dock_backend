package tech.dock.desafio.validators;

import javax.validation.ValidationException;

public interface AppValidator<T> {

	public void validate(T t) throws ValidationException;	
	
	
}