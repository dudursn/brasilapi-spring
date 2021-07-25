package io.github.dudursn.brasilapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String sigla;

    @NotNull
    private String nome;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;


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
        return regiao;
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
        this.regiao = regiao;
    }
}
