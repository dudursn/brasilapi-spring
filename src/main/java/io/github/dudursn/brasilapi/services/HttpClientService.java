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

        String content = "";
        try {
            HttpResponse response = client.execute(request);

            content = Util.BufferToString(response.getEntity().getContent());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }


    public static String post(String uri, String data){

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

        String content = "";
        try {

            HttpResponse response = client.execute(request);
            content = Integer.toString(response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }

    public static String put(String uri, String data){

        HttpClient client = HttpClients.custom().build();
        StringEntity entity = null;

        try {
            entity = new StringEntity(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUriRequest request = RequestBuilder
                .put(uri)
                .setEntity(entity)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application-json")
                .build();

        String content = "";
        try {

            HttpResponse response = client.execute(request);
            content = Integer.toString(response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }

    public static String delete(String uri){

        HttpClient client = HttpClients.custom().build();

        HttpUriRequest request = RequestBuilder
                .delete(uri)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application-json")
                .build();

        String content = "";
        try {

            HttpResponse response = client.execute(request);
            content = Integer.toString(response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }

}
