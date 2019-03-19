package com.example.grudzina_bakowski.Fryzjer.HandlerHTTP;

import android.os.AsyncTask;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionToDatabase;

public class DeleteData extends AsyncTask<String, String, String> {


    private String data;
    private String user;

    public DeleteData(String connectionName, String user) {
        this.data = connectionName;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... strings) {

        ConnectionToDatabase connectionToDatabase = new ConnectionToDatabase();
        HTTPDateHandler httpDateHandler = new HTTPDateHandler();
        String json = user;
        httpDateHandler.DeleteHTTPData(connectionToDatabase.getAddressAPI(data), json);
        return null;
    }
}