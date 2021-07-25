package io.github.dudursn.brasilapi.models;

import io.github.dudursn.brasilapi.utils.Util;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class FeriadoNacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;

    @NotNull
    private String name;

    @NotNull
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

