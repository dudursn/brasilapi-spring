package io.github.dudursn.brasilapi.models;

public class FeriadoNacional {

    private long id;
    private String date;
    private String name;
    private String type;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}

