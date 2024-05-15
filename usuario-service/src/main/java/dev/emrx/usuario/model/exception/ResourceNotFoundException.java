package dev.emrx.usuario.model.exception;

import org.springframework.beans.factory.annotation.Value;

public class ResourceNotFoundException extends RuntimeException {

//    @Value("${error.custom.message}")
//    private String mensaje;

    public ResourceNotFoundException() {
        super("Recurso no encontrado en el servidor");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
