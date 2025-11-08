package com.clinica.clinica_plus.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===============================
    // 1️⃣ Erro de entidade não encontrada
    // ===============================
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        erro.put("status", HttpStatus.NOT_FOUND.value());
        erro.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // ===============================
    // 2️⃣ Erro de argumento inválido (validação @Valid)
    // ===============================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> erros = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("erro", "Campos inválidos");
        resposta.put("detalhes", erros);
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    // ===============================
    // 3️⃣ Erros de regras de negócio (ex: CPF duplicado)
    // ===============================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        erro.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // ===============================
    // 4️⃣ Fallback geral (qualquer outro erro)
    // ===============================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", "Erro interno no servidor: " + ex.getMessage());
        erro.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        erro.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
