package io.github.dudursn.brasilapi.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dudursn.brasilapi.models.Bank;
import io.github.dudursn.brasilapi.utils.Util;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpHeaders;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BrasilApiService {

    private static final String URL_BASE = "https://brasilapi.com.br/api/";

    public static Bank[] getBanks(){

        String uri = URL_BASE + "/banks/v1";

        HttpClient client = HttpClients.custom().build();

        HttpUriRequest request = RequestBuilder.get(uri)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application-json")
                .build();

        HttpResponse response = null;
        Bank[] banks = new Bank[0];

        try {
            response = client.execute(request);

            String content = Util.BufferToString(response.getEntity().getContent());

            banks = new ObjectMapper().readValue(content, Bank [].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return banks;
    }


}
