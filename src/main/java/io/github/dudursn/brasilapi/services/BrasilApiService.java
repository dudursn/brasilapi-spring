package io.github.dudursn.brasilapi.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dudursn.brasilapi.models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class BrasilApiService {

    private static final String URL_BASE = "https://brasilapi.com.br/api/";

    public static Bank[] getBanks(){

        String uri = URL_BASE + "/banks/v1";

        Bank[] banks = new Bank[0];

        try {
            String content = HttpClientService.get(uri);

            banks = new ObjectMapper().readValue(content, Bank [].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return banks;
    }

    public static Cep getCep(String cepStr){

        String uri = URL_BASE + "/cep/v1/"+ cepStr;

        Cep cep = new Cep();

        try {

            String content = HttpClientService.get(uri);

            cep = new ObjectMapper().readValue(content, Cep.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cep;
    }


    public static Cnpj getCnpj(String cnpjStr){

        String uri = URL_BASE + "/cnpj/v1/"+ cnpjStr;

        Cnpj cnpj = new Cnpj();


        try {

            String content = HttpClientService.get(uri);
            cnpj = new ObjectMapper().readValue(content, Cnpj.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cnpj;
    }

    public static DddEstadoCidade getDddEstadoCidade(String ddd){

        String uri = URL_BASE + "/ddd/v1/"+ ddd;

        DddEstadoCidade dddEstadoCidade = new DddEstadoCidade();


        String content = HttpClientService.get(uri);

        try{
            JSONObject obj = new JSONObject(content);

            if (obj.has("state")) {

                dddEstadoCidade.setState(obj.getString("state"));
                JSONArray cities = obj.getJSONArray("cities");
                String citiesStr = "";
                for (int i = 0; i < cities.length(); i++) {

                   citiesStr += (String) cities.get(i) + ",";
                }
                citiesStr = citiesStr.substring(0, citiesStr.length() - 1);
                dddEstadoCidade.setCities(citiesStr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return dddEstadoCidade;

    }
    public static FeriadoNacional[] getFeriadosNacional(String ano){

        String uri = URL_BASE + "/feriados/v1/"+ ano;

        FeriadoNacional[] feriadosNacional = new FeriadoNacional[0];

        try {

            String content = HttpClientService.get(uri);
            feriadosNacional = new ObjectMapper().readValue(content, FeriadoNacional [].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return feriadosNacional;
    }

    public static TabelaFipe[] getTabelasFipe(){

        String uri = URL_BASE + "/fipe/tabelas/v1";

        TabelaFipe[] tabelasFipe = new TabelaFipe[0];

        try {
            String content = HttpClientService.get(uri);

            tabelasFipe = new ObjectMapper().readValue(content, TabelaFipe [].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tabelasFipe;
    }

    public static Marca[] getMarcas(String tipoVeiculo){

        String uri = URL_BASE + "/fipe/marcas/v1/"+ tipoVeiculo;

        Marca[] marcas = new Marca[0];

        try {

            String content = HttpClientService.get(uri);
            marcas = new ObjectMapper().readValue(content, Marca [].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return marcas;
    }

    public static Preco[] getPrecos(String codigoFipe){

        String uri = URL_BASE + "/fipe/preco/v1/" + codigoFipe;

        Preco[] precos = new Preco[0];

        try {

            String content = HttpClientService.get(uri);

            precos = new ObjectMapper().readValue(content, Preco [].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return precos;
    }

    public static Estado[] getEstados(){

        String uri = URL_BASE + "/ibge/uf/v1";

        Estado[] estados = new Estado[0];

        try {
            String content = HttpClientService.get(uri);

            estados = new ObjectMapper().readValue(content, Estado [].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return estados;
    }

}
