package io.github.dudursn.brasilapi.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dudursn.brasilapi.models.Bank;
import io.github.dudursn.brasilapi.models.Cep;
import io.github.dudursn.brasilapi.models.TabelaFipe;

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


   /* //public static Cnpj getCnpj(String cnpjStr){
    public static String getCnpj(String cnpjStr){

        String uri = URL_BASE + "/cnpj/v1/"+ cnpjStr;

        //Cnpj cnpj = new Cnpj();


        try {

            String content = HttpClientService.get(uri);

            //cnpj = new ObjectMapper().readValue(content, Cep.class);

            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "cnpj";

    }*/

   /* //public static FeriadoNacional getFeriadoNacional(String feriadoNacionalStr){
    public static String getFeriadoNacional(String feriadoNacionalStr){

        String uri = URL_BASE + "/fipe/marcas/v1/"+ feriadoNacionalStr;

        //FeriadoNacional feriadoNacional = new FeriadoNacional();

        try {

            String content = HttpClientService.get(uri);

            //feriadoNacional = new ObjectMapper().readValue(content, Cep.class);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "feriadoNacional";
    }
*/
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

   /* //public static TipoVeiculo getTipoVeiculo(String tipoVeiculoStr){
    public static String getTipoVeiculo(String tipoVeiculoStr){

        String uri = URL_BASE + "/fipe/marcas/v1/"+ tipoVeiculoStr;

        //TipoVeiculo tipoVeiculo = new TipoVeiculo();

        try {

            String content = HttpClientService.get(uri);

            //tipoVeiculo = new ObjectMapper().readValue(content, Cep.class);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "tipoVeiculo";
    }*/


}
