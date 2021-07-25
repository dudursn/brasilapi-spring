package io.github.dudursn.brasilapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "cnae_secundario")
@Entity
public class CnaeSecundario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull
    private String codigo;

    @NotNull
    private String descricao;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
