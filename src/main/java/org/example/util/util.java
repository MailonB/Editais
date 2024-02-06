package org.example.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;


public class util {

    public static Document toParser(String body) {
        return Jsoup.parse(body);
    }

    public static JsonObject toJSON(String body) {
        return new Gson().fromJson(body, JsonObject.class);
    }

    public static String dateToString(String data) {

        Date date = new Date(extractTimestamp(data));

        // Formatar a data como DD/MM/YYYY
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(date);

        return formattedDate;
    }


    private static long extractTimestamp(String dateString) {
        // Remover caracteres não numéricos da string
        return Long.parseLong(dateString.replaceAll("[^\\d]", ""));
    }
}





