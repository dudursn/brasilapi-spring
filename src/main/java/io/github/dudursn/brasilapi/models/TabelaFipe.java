package io.github.dudursn.brasilapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TabelaFipe {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long codigo;

    @NotNull
    private String mes;


    // Getter Methods

    public long getId() {
        return id;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getMes() {
        return mes;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
