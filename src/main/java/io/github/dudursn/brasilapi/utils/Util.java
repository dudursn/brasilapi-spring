package io.github.dudursn.brasilapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static Date StringToDateServidor(String dt){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        try {
            date = formatter.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToStringWeb(Date date){

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String dataStr = df.format(date);

        return dataStr;
    }
}
