package com.example.grudzina_bakowski.Fryzjer.TabViewADMIN;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.Entity.Price;
import com.example.grudzina_bakowski.Fryzjer.HandlerHTTP.PostData;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdminPriceListEdit extends AppCompatActivity {
    private Button bSave;
    private Button bDelete;
    private TextView serviceName;
    private TextView hShortPrice;
    private TextView hMediumPrice;
    private TextView hLongPrice;
    private TextView serviceTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricelist_edit);
        Intent intent = getIntent();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        bSave = findViewById(R.id.buttonSavePrice);
        bDelete = findViewById(R.id.buttonDeletePrice);

        serviceName = findViewById(R.id.serviceName);
        hShortPrice = findViewById(R.id.priceShort);
        hMediumPrice = findViewById(R.id.priceMedium);
        hLongPrice = findViewById(R.id.priceLong);
        serviceTime = findViewById(R.id.serviceTime);

        try {
            serviceName.setText(intent.getStringExtra("serviceName"));
            hShortPrice.setText(intent.getStringExtra("hShort"));
            hMediumPrice.setText(intent.getStringExtra("hMedium"));
            hLongPrice.setText(intent.getStringExtra("hLong"));
            serviceTime.setText(intent.getStringExtra("serviceTime"));
        } catch (Exception e) {
            serviceName.setText("");
            hShortPrice.setText("");
            hMediumPrice.setText("");
            hLongPrice.setText("");
            serviceTime.setText("");
        }

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(getApplicationContext());
                List<Price> priceList = sqLiteCRUD.getAllPrices();
                for (Price price : priceList) {
                    if (price.getServiceName().equals(serviceName.getText())) {
                        //TODO: delete existing price date
                        //all 3 of those (short, medium, long)
                        break;
                    }
                }

                ConnectionName connectionName;
                connectionName = new ConnectionName();

                try {
                    String jsonString = new JSONObject()
                            .put("nazwa", serviceName.getText())
                            .put("krotkie", hShortPrice.getText())
                            .put("srednie", hMediumPrice.getText())
                            .put("dlugie", hLongPrice.getText())
                            .put("czas", serviceTime.getText()).toString();
                    new PostData(connectionName.getCOLLECTION_NAME_PRICE(), jsonString).execute();// tutaj wywoluje
                    System.out.println(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("KLIK: Save price");
                finish();
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(getApplicationContext());
                List<Price> priceList = sqLiteCRUD.getAllPrices();
                for (Price price : priceList) {
                    if (price.getServiceName().equals(serviceName.getText())) {
                        //TODO: delete price date
                        //all 3 of those (short, medium, long)
                        break;
                    }
                }
                System.out.println("KLIK: Delete price");

                finish();
//                setResult(Activity.RESULT_OK,MainActivityAdmin);
            }
        });
    }
}
