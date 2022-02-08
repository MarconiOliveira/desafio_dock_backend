package tech.dock.desafio.dto;

import java.io.Serializable;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;

import tech.dock.desafio.exceptions.ErroInesperadoException;
import tech.dock.desafio.exceptions.NegocioException;
import tech.dock.desafio.exceptions.ObjetoExistenteException;
import tech.dock.desafio.exceptions.ObjetoNaoEncontradoException;
import tech.dock.desafio.exceptions.PermissaoNegadaException;
import tech.dock.desafio.exceptions.ValidacaoException;

public class ErroDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String mensagem;
    private Long timeStamp;

    public ErroDTO(Integer status, String mensagem, Long timeStamp) {
        this.status = status;
        this.mensagem = mensagem;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public static ErroDTO criar(ObjetoNaoEncontradoException e) {
    	return criar(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    
    public static ErroDTO criar(ObjectNotFoundException e) {
    	return criar(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    
    public static ErroDTO criar(ErroInesperadoException e) {
    	return criar(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
    
    public static ErroDTO criar(NegocioException e) {
    	return criar(HttpStatus.OK.value(), e.getMessage());
    }
    
    public static ErroDTO criar(ObjetoExistenteException e) {
    	return criar(HttpStatus.CONFLICT.value(), e.getMessage());
    }
    
    public static ErroDTO criar(PermissaoNegadaException e) {
    	return criar(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
    
    public static ErroDTO criar(ValidacaoException e) {
    	return criar(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    
    public static ErroDTO criar(Throwable e) {
    	return criar(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
    
    public static ErroDTO criar(int status, String mensagem) {
    	return new ErroDTO(status, mensagem, System.currentTimeMillis());
    }

}

