package io.github.dudursn.brasilapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nome;

    @NotNull
    private int valor;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
