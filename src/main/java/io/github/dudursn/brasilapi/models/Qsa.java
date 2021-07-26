package io.github.dudursn.brasilapi.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "qsa")
@Entity
public class Qsa {

    @Id
    private long identificador_de_socio;

    @NotNull
    private String nome_socio;

    @NotNull
    private String cnpj_cpf_do_socio;

    @NotNull
    private int codigo_qualificacao_socio;

    @NotNull
    private String percentual_capital_social;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String data_entrada_sociedade;

    @Column
    private String cpf_representante_legal;

    @Column
    private String nome_representante_legal;

    @Column
    private String codigo_qualificacao_representante_legal;

    @ManyToOne
    @JoinColumn(name = "cnpj_id", nullable = true)
    private Cnpj cnpj;

    // Getter Methods

    public long getIdentificador_de_socio() {
        return identificador_de_socio;
    }

    public String getNome_socio() {
        return nome_socio;
    }

    public String getCnpj_cpf_do_socio() {
        return cnpj_cpf_do_socio;
    }

    public float getCodigo_qualificacao_socio() {
        return codigo_qualificacao_socio;
    }

    public String getPercentual_capital_social() {
        return percentual_capital_social;
    }

    public String getData_entrada_sociedade() {
        return data_entrada_sociedade;
    }

    public String getCpf_representante_legal() {
        return cpf_representante_legal;
    }

    public String getNome_representante_legal() {
        return nome_representante_legal;
    }

    public String getCodigo_qualificacao_representante_legal() {
        return codigo_qualificacao_representante_legal;
    }

    public Cnpj getCnpj() {
        return cnpj;
    }


    // Setter Methods

    public void setIdentificador_de_socio(long identificador_de_socio) {
        this.identificador_de_socio = identificador_de_socio;
    }

    public void setNome_socio(String nome_socio) {
        this.nome_socio = nome_socio;
    }

    public void setCnpj_cpf_do_socio(String cnpj_cpf_do_socio) {
        this.cnpj_cpf_do_socio = cnpj_cpf_do_socio;
    }

    public void setCodigo_qualificacao_socio(int codigo_qualificacao_socio) {
        this.codigo_qualificacao_socio = codigo_qualificacao_socio;
    }

    public void setPercentual_capital_social(String percentual_capital_social) {
        this.percentual_capital_social = percentual_capital_social;
    }

    public void setData_entrada_sociedade(String data_entrada_sociedade) {
        this.data_entrada_sociedade = data_entrada_sociedade;
    }

    public void setCpf_representante_legal(String cpf_representante_legal) {
        this.cpf_representante_legal = cpf_representante_legal;
    }

    public void setNome_representante_legal(String nome_representante_legal) {
        this.nome_representante_legal = nome_representante_legal;
    }

    public void setCodigo_qualificacao_representante_legal(String codigo_qualificacao_representante_legal) {
        this.codigo_qualificacao_representante_legal = codigo_qualificacao_representante_legal;
    }

    public void setCnpj(Cnpj cnpj) {
        this.cnpj = cnpj;
    }
}



