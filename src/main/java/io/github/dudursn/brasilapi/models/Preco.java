package io.github.dudursn.brasilapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Preco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String valor;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private int anoModelo;

    @NotNull
    private String combustivel;

    @NotNull
    private String codigoFipe;

    @NotNull
    private String mesReferencia;

    @NotNull
    private int tipoVeiculo;

    @NotNull
    private char siglaCombustivel;

    @NotNull
    private String dataConsulta;


    // Getter Methods

    public long getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public char getSiglaCombustivel() {
        return siglaCombustivel;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public void setSiglaCombustivel(char siglaCombustivel) {
        this.siglaCombustivel = siglaCombustivel;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

}
