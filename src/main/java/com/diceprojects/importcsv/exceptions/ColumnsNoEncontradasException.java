package com.diceprojects.importcsv.exceptions;

public class ColumnsNoEncontradasException extends RuntimeException {
    private String fileName;

    public ColumnsNoEncontradasException(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

