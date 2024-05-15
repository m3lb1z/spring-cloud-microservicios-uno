package dev.emrx.usuario.infra.exceptions;

import dev.emrx.usuario.model.exception.ResourceNotFoundException;
import dev.emrx.usuario.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        String mensaje = ex.getMessage();
        ApiResponse response = ApiResponse.builder()
                .message(mensaje)
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
