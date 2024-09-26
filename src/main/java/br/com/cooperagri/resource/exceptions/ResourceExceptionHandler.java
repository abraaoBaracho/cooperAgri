package br.com.cooperagri.resource.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cooperagri.services.exceptions.ResouceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<StandardError> recursoNaoEncontrado(ResouceNotFoundException e, HttpServletRequest request) {
        String erro = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
