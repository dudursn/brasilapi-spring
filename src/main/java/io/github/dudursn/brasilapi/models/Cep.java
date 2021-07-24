package io.github.dudursn.brasilapi.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String cep;

    @NotNull
    private String state;

    @NotNull
    private String city;

    @NotNull
    private String neighborhood;

    @NotNull
    private String street;

    @NotNull
    private String service;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getService() {
        return service;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setService(String service) {
        this.service = service;
    }
}