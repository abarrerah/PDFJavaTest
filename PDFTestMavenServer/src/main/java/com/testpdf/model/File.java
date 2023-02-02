package com.testpdf.model;

import java.util.UUID;

public class File {

    private UUID id;

    private String name;

    private Boolean isChecked;

    private String path;

    
    public File(String name, Boolean check, String path) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.isChecked = check;
        this.path = path;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name ) {
        this.name = name;
    }

    public Boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(Boolean isChecked ) {
        this.isChecked = isChecked;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
