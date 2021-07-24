package io.github.dudursn.brasilapi.services;

import io.github.dudursn.brasilapi.utils.Util;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpClientService {

    public static String get(String uri){

        HttpClient client = HttpClients.custom().build();

        HttpUriRequest request = RequestBuilder.get(uri)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application-json")
                .build();

        HttpResponse response = null;
        String content = "";
        try {
            response = client.execute(request);

            content = Util.BufferToString(response.getEntity().getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }


    public static int post(String uri, String data){
        uri = "https://reqres.in/api/users";
        data = "{" +
                    "'name:'Joao," +
                    "job:'Ttesteando'" +
                "}";
        HttpClient client = HttpClients.custom().build();
        StringEntity entity = null;

        try {
            entity = new StringEntity(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUriRequest request = RequestBuilder
                .post(uri)
                .setEntity(entity)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application-json")
                .build();


        HttpResponse response = null;
        int content = 0;
        try {
            response = client.execute(request);
            content = response.getStatusLine().getStatusCode();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }

}
