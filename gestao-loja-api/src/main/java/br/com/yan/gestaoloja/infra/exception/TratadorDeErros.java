package br.com.yan.gestaoloja.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        List dadosErros = erros.stream()
                .map(DadosErroValidacao::new)
                .toList();

        return ResponseEntity.badRequest().body(dadosErros);

    }
 }
