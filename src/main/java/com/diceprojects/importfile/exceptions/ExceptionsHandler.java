package com.diceprojects.importfile.exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase que maneja las excepciones de la aplicación.
 */
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja excepciones del tipo ToDoExceptions.
     *
     * @param ex      Excepción ToDoExceptions.
     * @param request Petición web.
     * @return ResponseEntity con detalles de la excepción.
     */
    @ExceptionHandler(value = {ToDoExceptions.class})
    protected ResponseEntity<Object> handConflict(ToDoExceptions ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }

    /**
     * Maneja excepciones del tipo ColumnsNoEncontradasException.
     *
     * @param ex Excepción ColumnsNoEncontradasException.
     * @return ErrorResponse con el mensaje de error.
     */
    @ExceptionHandler(ColumnsNoEncontradasException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleColumnsNoEncontradas(ColumnsNoEncontradasException ex) {
        String mensaje = "No existe configuración de las columnas del archivo para la operación " + ex.getFileName();
        return new ErrorResponse(mensaje);
    }
}
