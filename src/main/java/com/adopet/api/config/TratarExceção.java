package com.adopet.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Stream;

@RestControllerAdvice
public class TratarExceção {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errors = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(errors.stream().map(fieldError -> new DadosErroCadastro(fieldError)));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity tratarErroNotFound(NotFoundException ex) {
        var error = ex.getMessage();
        return ResponseEntity.badRequest()
                .body(new DadosErroBuscarRegistros(error));
    }

    public record DadosErroCadastro(String campo, String erro) {
        public DadosErroCadastro(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public record DadosErroBuscarRegistros(String erro) {

    }

}
