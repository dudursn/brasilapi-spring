package io.github.dudursn.brasilapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String sigla;

    @NotNull
    private String nome;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Estado> estados;


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

