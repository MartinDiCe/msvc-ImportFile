package com.diceprojects.importcsv.exceptions;

public class ColumnsNoEncontradasException extends RuntimeException {
    private String operacion;

    public ColumnsNoEncontradasException(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }
}

