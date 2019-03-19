package com.example.grudzina_bakowski.Fryzjer.HandlerHTTP;

import android.os.AsyncTask;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionToDatabase;

public class PostData extends AsyncTask<String, String, String> {


    private String data;
    private String user;

    public PostData(String connectionName, String user) {
        this.data = connectionName;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... strings) {

        ConnectionToDatabase connectionToDatabase = new ConnectionToDatabase();
        HTTPDateHandler httpDateHandler = new HTTPDateHandler();
        String json = user;
        httpDateHandler.PostHTTPData(connectionToDatabase.getAddressAPI(data), json);

        return null;
    }
}
