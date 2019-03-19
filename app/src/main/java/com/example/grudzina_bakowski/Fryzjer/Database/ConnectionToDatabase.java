package com.example.grudzina_bakowski.Fryzjer.Database;

import com.example.grudzina_bakowski.Fryzjer.Entity.User;

public class ConnectionToDatabase {
    private static String DB_NAME="//";
    public static String API_KEY="//";

    public static String getAddress(User user,String COLLECTION_NAME){
        String baseUrl=String.format("//",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+user.getId()+"?apiKey="+API_KEY);
       return stringBuilder.toString();
    }
    public static String getAddressAPI(String COLLECTION_NAME){
        String baseUrl=String.format("//",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

}
