package selton.dev.desafio_relogios.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import selton.dev.desafio_relogios.exception.ErrorResponse.ErroCampo;
import selton.dev.desafio_relogios.exception.custom.RelogioNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(RelogioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRelogioNaoEncontradoException(RelogioNaoEncontradoException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
            new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                ex.getLocalizedMessage(),
                request.getRequestURI(),
                List.of()
            ),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<ErrorResponse.ErroCampo> campos = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = null;
            for (Path.Node node : violation.getPropertyPath()) {
                fieldName = node.getName();
            }
            if (fieldName != null) {
                    campos.add(new ErroCampo(fieldName, violation.getMessage()));
                }
        }

        return new ResponseEntity<>(
            new ErrorResponse(
                Instant.now(), 
                HttpStatus.BAD_REQUEST.value(), 
                "Requisição inválida", 
                "Erro de validação", 
                request.getRequestURI(),
                campos
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
            new ErrorResponse(
                Instant.now(), 
                HttpStatus.BAD_REQUEST.value(), 
                "Requisição inválida", 
                ex.getLocalizedMessage(), 
                request.getRequestURI(),
                List.of()
            ),
            HttpStatus.BAD_REQUEST
        );
    }

}
