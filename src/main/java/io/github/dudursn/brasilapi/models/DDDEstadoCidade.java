package io.github.dudursn.brasilapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
public class DDDEstadoCidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String ddd;

    @NotNull
    private String state;

    ArrayList<Object> cities = new ArrayList<Object>();


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getDdd() {
        return ddd;
    }

    public String getState() {
        return state;
    }

    public ArrayList<Object> getCities() {
        return cities;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCities(ArrayList<Object> cities) {
        this.cities = cities;
    }
}
