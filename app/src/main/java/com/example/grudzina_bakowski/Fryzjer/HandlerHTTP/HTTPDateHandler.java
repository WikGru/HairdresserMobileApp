package com.example.grudzina_bakowski.Fryzjer.HandlerHTTP;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HTTPDateHandler {


    private JSONArray jsonarray;
    private static String stream = null;


    public JSONArray GetHTTPData(String urlstring) // poieranie danych z danej tabeli
    {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                System.out.println("HTTP OK");
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);

                }
                urlConnection.disconnect();
                jsonarray = new JSONArray(stringBuilder.toString());
            } else {

                System.out.println("HTTP NOT WORK");
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException f) {
            System.out.println(f);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonarray;
    }


    public void PostHTTPData(String urlstring, String json) {
        try {

            URL url = new URL(urlstring);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setChunkedStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset","utf-8");
            urlConnection.connect();
            try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                wr.write(out);
                System.out.println("        WYSLANE");
            }catch (Exception e){
                System.out.println(e);
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }catch (ProtocolException e){
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }


    public void DeleteHTTPData(String urlstring, String json) {
        try {
            System.out.println("  Wchodze DELETE");
            URL url = new URL(urlstring);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoOutput(true);
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setChunkedStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset","utf-8");
            urlConnection.connect();
            try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                wr.write(out);
                System.out.println("        WYSLANE DELETE");
            }catch (Exception e){
                System.out.println(e);
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }catch (ProtocolException e){
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}


