package io.github.dudursn.brasilapi.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Cnpj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    @NotNull
    private String cnpj;

    @NotNull
    private int identificador_matriz_filial;

    @NotNull
    private String descricao_matriz_filial;

    @NotNull
    private String razao_social;

    @NotNull
    private String nome_fantasia;

    @NotNull
    private int situacao_cadastral;

    @NotNull
    private String descricao_situacao_cadastral;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String data_situacao_cadastral;

    @NotNull
    private int motivo_situacao_cadastral;

    @Column(nullable = true)
    private String nome_cidade_exterior;

    @NotNull
    private int codigo_natureza_juridica;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String data_inicio_atividade;

    @NotNull
    private String cnae_fiscal;

    @NotNull
    private String cnae_fiscal_descricao;

    @NotNull
    private String descricao_tipo_logradouro;

    @NotNull
    private String logradouro;

    @NotNull
    private String numero;

    @Column(nullable = true)
    private String complemento;

    @NotNull
    private String bairro;

    @NotNull
    private String cep;

    @NotNull
    private String uf;

    @NotNull
    private int codigo_municipio;

    @NotNull
    private String municipio;


    @NotNull
    @Column(name="ddd_telefone_i")
    private String ddd_telefone_i;


    @Column(name="ddd_telefone_ii",nullable = true)
    private String ddd_telefone_ii;

    @Column(nullable = true)
    private String ddd_fax;

    @NotNull
    private int qualificacao_do_responsavel;

    @NotNull
    private int capital_social;

    @NotNull
    private int porte;

    @NotNull
    private String descricao_porte;

    @NotNull
    private boolean opcao_pelo_simples;

    @Column(nullable = true)
    private String data_opcao_pelo_simples = null;

    @Column(nullable = true)
    private String data_exclusao_do_simples = null;

    @NotNull
    private boolean opcao_pelo_mei;

    @Column(nullable = true)
    private String situacao_especial = null;

    @Column(nullable = true)
    private String data_situacao_especial = null;

    @ManyToMany
    @JoinTable(name = "cnpj_cnae_secundario",
            joinColumns = @JoinColumn(name = "cnpj_id"),
            inverseJoinColumns = @JoinColumn(name = "cnae_id"))
    private List<CnaeSecundario> cnaeSecundarios;

    @OneToMany(mappedBy = "cnpj")
    private List<Qsa> qsas;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public float getIdentificador_matriz_filial() {
        return identificador_matriz_filial;
    }

    public String getDescricao_matriz_filial() {
        return descricao_matriz_filial;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public int getSituacao_cadastral() {
        return situacao_cadastral;
    }

    public String getDescricao_situacao_cadastral() {
        return descricao_situacao_cadastral;
    }

    public String getData_situacao_cadastral() {
        return data_situacao_cadastral;
    }

    public int getMotivo_situacao_cadastral() {
        return motivo_situacao_cadastral;
    }

    public String getNome_cidade_exterior() {
        return nome_cidade_exterior;
    }

    public int getCodigo_natureza_juridica() {
        return codigo_natureza_juridica;
    }

    public String getData_inicio_atividade() {
        return data_inicio_atividade;
    }

    public String getCnae_fiscal() {
        return cnae_fiscal;
    }

    public String getCnae_fiscal_descricao() {
        return cnae_fiscal_descricao;
    }

    public String getDescricao_tipo_logradouro() {
        return descricao_tipo_logradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return uf;
    }

    public float getCodigo_municipio() {
        return codigo_municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getDdd_telefone_i() {
        return ddd_telefone_i;
    }

    public String getDdd_telefone_ii() {
        return ddd_telefone_ii;
    }

    public String getDdd_fax() {
        return ddd_fax;
    }

    public int getQualificacao_do_responsavel() {
        return qualificacao_do_responsavel;
    }

    public int getCapital_social() {
        return capital_social;
    }

    public int getPorte() {
        return porte;
    }

    public String getDescricao_porte() {
        return descricao_porte;
    }

    public boolean getOpcao_pelo_simples() {
        return opcao_pelo_simples;
    }

    public String getData_opcao_pelo_simples() {
        return data_opcao_pelo_simples;
    }

    public String getData_exclusao_do_simples() {
        return data_exclusao_do_simples;
    }

    public boolean getOpcao_pelo_mei() {
        return opcao_pelo_mei;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public String getData_situacao_especial() {
        return data_situacao_especial;
    }

    public List<CnaeSecundario> getCnaeSecundarios() {
        return cnaeSecundarios;
    }

    public List<Qsa> getQsas() {
        return qsas;
    }


    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setIdentificador_matriz_filial(int identificador_matriz_filial) {
        this.identificador_matriz_filial = identificador_matriz_filial;
    }

    public void setDescricao_matriz_filial(String descricao_matriz_filial) {
        this.descricao_matriz_filial = descricao_matriz_filial;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public void setSituacao_cadastral(int situacao_cadastral) {
        this.situacao_cadastral = situacao_cadastral;
    }

    public void setDescricao_situacao_cadastral(String descricao_situacao_cadastral) {
        this.descricao_situacao_cadastral = descricao_situacao_cadastral;
    }

    public void setData_situacao_cadastral(String data_situacao_cadastral) {
        this.data_situacao_cadastral = data_situacao_cadastral;
    }

    public void setMotivo_situacao_cadastral(int motivo_situacao_cadastral) {
        this.motivo_situacao_cadastral = motivo_situacao_cadastral;
    }

    public void setNome_cidade_exterior(String nome_cidade_exterior) {
        this.nome_cidade_exterior = nome_cidade_exterior;
    }

    public void setCodigo_natureza_juridica(int codigo_natureza_juridica) {
        this.codigo_natureza_juridica = codigo_natureza_juridica;
    }

    public void setData_inicio_atividade(String data_inicio_atividade) {
        this.data_inicio_atividade = data_inicio_atividade;
    }

    public void setCnae_fiscal(String cnae_fiscal) {
        this.cnae_fiscal = cnae_fiscal;
    }

    public void setCnae_fiscal_descricao(String cnae_fiscal_descricao) {
        this.cnae_fiscal_descricao = cnae_fiscal_descricao;
    }

    public void setDescricao_tipo_logradouro(String descricao_tipo_logradouro) {
        this.descricao_tipo_logradouro = descricao_tipo_logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCodigo_municipio(int codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setDdd_telefone_i(String ddd_telefone_i) {
        this.ddd_telefone_i = ddd_telefone_i;
    }

    public void setDdd_telefone_ii(String ddd_telefone_ii) {
        this.ddd_telefone_ii = ddd_telefone_ii;
    }

    public void setDdd_fax(String ddd_fax) {
        this.ddd_fax = ddd_fax;
    }

    public void setQualificacao_do_responsavel(int qualificacao_do_responsavel) {
        this.qualificacao_do_responsavel = qualificacao_do_responsavel;
    }

    public void setCapital_social(int capital_social) {
        this.capital_social = capital_social;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public void setDescricao_porte(String descricao_porte) {
        this.descricao_porte = descricao_porte;
    }

    public void setOpcao_pelo_simples(boolean opcao_pelo_simples) {
        this.opcao_pelo_simples = opcao_pelo_simples;
    }

    public void setData_opcao_pelo_simples(String data_opcao_pelo_simples) {
        this.data_opcao_pelo_simples = data_opcao_pelo_simples;
    }

    public void setData_exclusao_do_simples(String data_exclusao_do_simples) {
        this.data_exclusao_do_simples = data_exclusao_do_simples;
    }

    public void setOpcao_pelo_mei(boolean opcao_pelo_mei) {
        this.opcao_pelo_mei = opcao_pelo_mei;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public void setData_situacao_especial(String data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public void setCnaeSecundarios(List<CnaeSecundario> cnaeSecundarios) {
        this.cnaeSecundarios = cnaeSecundarios;
    }

    public void setQsas(List<Qsa> qsas) {
        this.qsas = qsas;
    }

}

