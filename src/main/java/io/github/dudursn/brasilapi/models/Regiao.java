package io.github.dudursn.brasilapi.models;

public class Regiao {

    private long id;
    private String sigla;
    private String nome;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

