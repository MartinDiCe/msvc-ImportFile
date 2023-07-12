package com.diceprojects.importcsv.exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ToDoExceptions.class})

    protected ResponseEntity <Object> handConflict (ToDoExceptions ex, WebRequest request){

        String bodyOfResponse = ex.getMessage();

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getHttpStatus(), request);

    }

    @ExceptionHandler(ColumnasNoEncontradasException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleColumnasNoEncontradas(ColumnasNoEncontradasException ex) {
        String mensaje = "No existe configuración de las columnas del archivo para la operación " + ex.getOperacion();
        return new ErrorResponse(mensaje);
    }

}
