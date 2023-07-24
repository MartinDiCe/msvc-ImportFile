package com.diceprojects.importcsv.persistences.models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Columns {

    private FileColumnsHeader header;
    private FileColumnsDetails details;

    public Columns() {
    }
}
