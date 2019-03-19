package com.example.grudzina_bakowski.Fryzjer.HandlerHTTP;

import android.os.AsyncTask;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionToDatabase;

import org.json.JSONArray;

public class GetData extends AsyncTask<String, Void, JSONArray> {

    public JSONArray response;
    private String data;

    public GetData(String connectionName) {
        this.data = connectionName;
    }


    @Override
    protected JSONArray doInBackground(String... strings) {
        ConnectionToDatabase connectionToDatabase = new ConnectionToDatabase();
        HTTPDateHandler httpDateHandler = new HTTPDateHandler();
        response = null;
        try {
            response = httpDateHandler.GetHTTPData(connectionToDatabase.getAddressAPI(data));
                System.out.println(response.toString(2)); // tutaj pobieram jsona i wywalam na console

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArr) {
        super.onPostExecute(jsonArr);
    }
}
