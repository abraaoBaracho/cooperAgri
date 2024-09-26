package br.com.cooperagri.services.exceptions;

public class ResouceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResouceNotFoundException(Object id){
        super("Recurso não encontrado. id " + id);
    }

}
