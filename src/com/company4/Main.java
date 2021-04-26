package com.company4;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.*;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

        public static final String END_OF_URL = "&country=russia&format=json&polygon_geojson=1";
        public static final String BEGIN_OF_URL = "https://nominatim.openstreetmap.org/search?";

        public static void main(String[] args) throws IOException, JSONException {

                System.out.println("Введите параметр для поиска (1 - край/область, 2 - федеральный округ): ");

                InputStream inputStream = System.in;
                Reader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String temp = bufferedReader.readLine();
                int number = Integer.parseInt(temp);

                if(number == 1) {

                        System.out.println("Введите край/область для поиска: ");
                        String searchName = bufferedReader.readLine();
                        String str = BEGIN_OF_URL + "state=" + searchName + END_OF_URL;

                        String json = IOUtils.toString(new URL(str), Charset.forName("UTF-8"));

                        int i = json.indexOf("{");
                        json = json.substring(i);
                        int p = json.lastIndexOf("}");
                        json = json.substring(i - 1, p + 1);
                        json = json.replaceAll("\\\\", "");

                        org.json.JSONObject json1 = new org.json.JSONObject(json.trim());
                        JSONObject jsonObject = new JSONObject(json);

                        System.out.print("Полное название: ");
                        System.out.println(jsonObject.get("display_name"));

                        System.out.print("Тип: ");
                        System.out.println(jsonObject.get("type"));

                        System.out.print("Координаты центра запрашиваемого объекта: ");
                        System.out.print(jsonObject.get("lat"));
                        System.out.print("\t");
                        System.out.println(jsonObject.get("lon"));

                        System.out.print("Массив координат объекта: ");
                        System.out.println(jsonObject.get("geojson"));
                }

                if(number == 2){
                        System.out.println("Введите федеральный округ для поиска: ");
                        String searchName = bufferedReader.readLine();

                        String str = BEGIN_OF_URL + "q=" + searchName + END_OF_URL;

                        String json = IOUtils.toString(new URL(str), Charset.forName("UTF-8"));

                        int i = json.indexOf("{");
                        json = json.substring(i);
                        int p = json.lastIndexOf("}");
                        json = json.substring(i - 1, p + 1);
                        json = json.replaceAll("\\\\", "");

                        org.json.JSONObject json1 = new org.json.JSONObject(json.trim());
                        JSONObject jsonObject = new JSONObject(json);

                        //JSONObject json = readJsonFromUrl(URL);
                        //System.out.println(jsonObject);
                        System.out.print("Полное название: ");
                        System.out.println(jsonObject.get("display_name"));

                        System.out.print("Тип: ");
                        System.out.println(jsonObject.get("type"));

                        System.out.print("Координаты центра запрашиваемого объекта: ");
                        System.out.print(jsonObject.get("lat"));
                        System.out.print("\t");
                        System.out.println(jsonObject.get("lon"));

                        System.out.print("Массив координат объекта: ");
                        System.out.println(jsonObject.get("geojson"));
                }
        }
}


