package com.example.grudzina_bakowski.Fryzjer.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionToDatabase;
import com.example.grudzina_bakowski.Fryzjer.Entity.User;
import com.example.grudzina_bakowski.Fryzjer.HandlerHTTP.HTTPDateHandler;
import com.example.grudzina_bakowski.Fryzjer.MainActivity;
import com.example.grudzina_bakowski.Fryzjer.MainActivityAdmin;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MongoToSQLite extends AsyncTask<String, Void, JSONArray> {
    private Context context;
    private JSONArray response;
    private JSONObject visitJsonObj;
    private JSONArray names;
    private SQLiteCRUD sqLiteCRUD;
    private ConnectionToDatabase connectionToDatabase;
    private ConnectionName connectionName;
    private HTTPDateHandler httpDateHandler;

    public MongoToSQLite(Context context) {
        this.context = context;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        connectionName = new ConnectionName();
        sqLiteCRUD = new SQLiteCRUD(context);
        sqLiteCRUD.clearPriceListTables();

        connectionToDatabase = new ConnectionToDatabase();
        httpDateHandler = new HTTPDateHandler();

        Price();
        ZapisWizyty();


        return response;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArr) {

        try {
            User isAdmin = sqLiteCRUD.getUser();
            if (isAdmin.getUser().equals("admin")) {
                context.startActivity(new Intent(context, MainActivityAdmin.class)); // głowna klasa dla admina
            } else {
                context.startActivity(new Intent(context, MainActivity.class)); // wywołanie głównej klasy
            }
        } catch (Exception e) {
            context.startActivity(new Intent(context, MainActivity.class)); // wywołanie głównej klasy
        }

    }

    private void Price() {
        response = null;
        try {
            response = httpDateHandler.GetHTTPData(connectionToDatabase.getAddressAPI(connectionName.getCOLLECTION_NAME_PRICE()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jsonArr = response;
        for (int i = 0; i < jsonArr.length(); i++) {
            try {
                JSONObject jsonObj = jsonArr.getJSONObject(i);

                String service = jsonObj.getString("nazwa");
                String hShort = jsonObj.getString("krotkie");
                String hMedium = jsonObj.getString("srednie");
                String hLong = jsonObj.getString("dlugie");
                String time = jsonObj.getString("czas");

                sqLiteCRUD.InsertPrice(service, hShort, hMedium, hLong, time);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void ZapisWizyty() {
        response = null;
        try {
            response = httpDateHandler.GetHTTPData(connectionToDatabase.getAddressAPI(connectionName.getCOLLECTION_NAME_ZAPIS_WIZYTY()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jsonArr = response;
        for (int i = 0; i < jsonArr.length(); i++) {
            try {
                visitJsonObj = jsonArr.getJSONObject(i);

                String service = visitJsonObj.getString("Rodzaj");
                String date = visitJsonObj.getString("Data");
                String timeBeg = visitJsonObj.getString("Godzina");
                String timeEnd = visitJsonObj.getString("Koniec");
                String name = visitJsonObj.getString("Imie");

                sqLiteCRUD.InsertVisit(service, date, timeBeg, timeEnd, name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
