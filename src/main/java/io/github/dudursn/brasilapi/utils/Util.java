package io.github.dudursn.brasilapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {

    public static long StringToLong(String str) {

        long id;
        try {
            id = Long.parseLong(str);

        } catch (NumberFormatException nfe) {

            id = 0;
        }

        return id;
    }

    public static String BufferToString(InputStream content) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                content));
        String line = "";
        String str = "";
        while ((line = rd.readLine()) != null) {
            str += line;
        }

        return str;
    }
}
