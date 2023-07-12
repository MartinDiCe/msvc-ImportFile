package com.diceprojects.importcsvmeli.exceptions;

public class ColumnasNoEncontradasException extends RuntimeException {
    private String operacion;

    public ColumnasNoEncontradasException(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }
}

