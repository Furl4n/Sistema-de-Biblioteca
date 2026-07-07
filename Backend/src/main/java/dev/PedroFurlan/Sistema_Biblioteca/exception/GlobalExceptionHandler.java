package dev.PedroFurlan.Sistema_Biblioteca.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //NOT FOUND (Status 404)
    @ExceptionHandler({ResourceNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<StandardError> handleNotFound(RuntimeException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, "Not Found", e.getMessage(), request);
    }

    // BUSINESS RULE (Status 400)
    @ExceptionHandler({BusinessRuleException.class, IllegalArgumentException.class})
    public ResponseEntity<StandardError> handleBusinessRules(RuntimeException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request / Business Rule", e.getMessage(), request);
    }

    // CREDENCIAIS (Status 401)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> handleBadCredentials(BadCredentialsException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", "The username or password is incorrect", request);
    }

    // PERMISSÕES (Status 403)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> handleAccessDenied(AccessDeniedException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, "Forbidden", "You are not authorized to access this resource", request);
    }

    // ERROS INESPERADOS (Status 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGenericException(HttpServletRequest request) {
        String secureMessage = "An unexpected error occurred on the server. Please try again later.";
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", secureMessage, request);
    }

    private ResponseEntity<StandardError> buildResponse(HttpStatus status, String error, String message, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}