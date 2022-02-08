package tech.dock.desafio.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tech.dock.desafio.dto.ErroDTO;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BindingResult result = ex.getBindingResult();

		List<String> errorList = new ArrayList<>();
		result.getFieldErrors().forEach((fieldError) -> {
			errorList.add(fieldError.getObjectName() + "." + fieldError.getField() + " : "
					+ fieldError.getDefaultMessage() + " : rejected value [" + fieldError.getRejectedValue() + "]");
		});
		result.getGlobalErrors().forEach((fieldError) -> {
			errorList.add(fieldError.getObjectName() + " : " + fieldError.getDefaultMessage());
		});

		//return ;
		return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST, ex.getMessage(), errorList), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErroDTO> handleException(ValidacaoException e, HttpServletRequest request) {
    	LOGGER.debug("erroDeValidacao", e);
    	ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroDTO> handleException(ObjectNotFoundException e, HttpServletRequest request) {
    	LOGGER.debug("objectNotFound", e);
    	ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
    
    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroDTO> handleException(ObjetoNaoEncontradoException e, HttpServletRequest request) {
    	LOGGER.debug("objetoNaoEncontrado", e);
    	ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
    
    @ExceptionHandler(ObjetoExistenteException.class)
    public ResponseEntity<ErroDTO> handleException(ObjetoExistenteException e, HttpServletRequest request) {
    	LOGGER.debug("objetoExistente", e);
    	ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
    

	@ExceptionHandler(ErroInesperadoException.class)
    public ResponseEntity<Object> handleException(ErroInesperadoException e, HttpServletRequest request) {
		LOGGER.error("erroInesperado", e);
		ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
	
	@ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleException(NegocioException e, HttpServletRequest request) {
		LOGGER.debug("negocioException", e);
		ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
	
	@ExceptionHandler(PermissaoNegadaException.class)
    public ResponseEntity<Object> handleException(PermissaoNegadaException e, HttpServletRequest request) {
		LOGGER.debug("permissaoNegada", e);
		ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
	
	@ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> erroInterno(Throwable e, HttpServletRequest request) {
		LOGGER.error("erroInterno", e);
		ErroDTO erro = ErroDTO.criar(e);
    	return new ResponseEntity<>(erro, HttpStatus.valueOf(erro.getStatus()));
	}
	
	public static class Error {
		private int errorCode;
		private String error;
		private String errorMessage;
		private List<String> fieldErrors = new ArrayList<String>();

		public Error(HttpStatus status, String message, List<String> fieldErrors) {
			this.errorCode = status.value();
			this.error = status.name();
			this.errorMessage = message;
			this.fieldErrors = fieldErrors;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public List<String> getFieldErrors() {
			return fieldErrors;
		}

		public void setFieldErrors(List<String> fieldErrors) {
			this.fieldErrors = fieldErrors;
		}
	}

}