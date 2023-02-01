package com.testpdf.model;

import java.util.UUID;

public class PDF {

    private UUID id;

    private String name;

    private Boolean isChecked;


    public PDF(String name, Boolean check) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.isChecked = check;
    }

    
}
