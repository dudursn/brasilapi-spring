package io.github.dudursn.brasilapi.models;

public class Estado {

    private long id;
    private String sigla;
    private String nome;
    Regiao Regiao;


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

    public Regiao getRegiao() {
        return Regiao;
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

    public void setRegiao(Regiao regiao) {
        this.Regiao = regiao;
    }
}
