package io.github.dudursn.brasilapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class DddEstadoCidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String state;

    @Lob
    @NotNull
    private String cities;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getCities() {
        return cities;
    }


    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }


}
