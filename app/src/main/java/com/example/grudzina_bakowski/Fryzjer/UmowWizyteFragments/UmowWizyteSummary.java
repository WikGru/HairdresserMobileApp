package com.example.grudzina_bakowski.Fryzjer.UmowWizyteFragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.Entity.UmowWizyteEncja;
import com.example.grudzina_bakowski.Fryzjer.HandlerHTTP.PostData;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;

import org.json.JSONException;
import org.json.JSONObject;

public class UmowWizyteSummary extends Fragment {
    Button nextButton;

    TextView usluga;
    TextView data;
    TextView godzina;
    TextView przedział_cenowy;
    EditText imie;

    String tKoniec;
    View myFragView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {


        myFragView = inflater.inflate(R.layout.fragment_umow_wizyte_summary, container, false);
        nextButton = myFragView.findViewById(R.id.nextButton3);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


        usluga = myFragView.findViewById(R.id.textView_service);
        data = myFragView.findViewById(R.id.textView_date);
        godzina = myFragView.findViewById(R.id.textView_time);
        przedział_cenowy = myFragView.findViewById(R.id.textView_pricerange);
        imie = myFragView.findViewById(R.id.imie_save);

        //Count timeDuration of selected service


        //Send visit to MongoDB
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Wizyta zapisana!");
                builder.setMessage("Rezerwacja fryzjerska dnia " + UmowWizyteEncja.getUmowWizyteEncja().getDate() + " o g." + UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg() + "  w salonie Fiona Żwirki i Wigury 1 86-050 Solec Kujawski ZAPRASZAMY! ");
                builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConnectionName connectionName;
                        connectionName = new ConnectionName();

                        SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());
                        sqLiteCRUD.InsertMojeWizyty(
                                UmowWizyteEncja.getUmowWizyteEncja().getService(),
                                UmowWizyteEncja.getUmowWizyteEncja().getDate(),
                                UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg());


                        tKoniec = CalculateServiceEndTime();

                        try {
                            String jsonString = new JSONObject()
                                    .put("Rodzaj", UmowWizyteEncja.getUmowWizyteEncja().getService())
                                    .put("Data", UmowWizyteEncja.getUmowWizyteEncja().getDate())
                                    .put("Godzina", UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg())
                                    .put("Koniec", tKoniec) //timeDuration
                                    .put("Imie", imie.getText().toString()).toString();
                            new PostData(connectionName.getCOLLECTION_NAME_ZAPIS_WIZYTY(), jsonString).execute();// tutaj wywoluje

                            System.out.println(jsonString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }).show();

            }
        });


        return myFragView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            UmowWizyteEncja umowWizyte = UmowWizyteEncja.getUmowWizyteEncja(); // singleton
            usluga.setText("Wybrana usługa: " + umowWizyte.getService());
            data.setText("Data wizyty: " + umowWizyte.getDate());
            godzina.setText("Godzina: " + umowWizyte.getTimeBeg());
            przedział_cenowy.setText("Przedział cenowy: " + umowWizyte.getPriceRange());
        }


    }

    public String CalculateServiceEndTime() {
        SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());
        int timeDuration = Integer.valueOf(sqLiteCRUD.getPrice(UmowWizyteEncja.getUmowWizyteEncja().getService()).getTime());
        int timeGodzina = Integer.valueOf(UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg().split(":")[0]);
        int timeMinuta = Integer.valueOf(UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg().split(":")[1]);
        //Count timeDuration (complicated but should do the work)
        while (timeDuration >= 60) {
            timeGodzina++;
            timeDuration -= 60;
        }
        while (timeDuration >= 15) {
            timeMinuta += 15;
            timeDuration -= 15;
        }
        while (timeMinuta >= 60) {
            timeGodzina++;
            timeMinuta -= 60;
        }
        if (timeMinuta == 0) {
            timeGodzina--;
            timeMinuta = 45;
        }
        return timeGodzina + ":" + timeMinuta;
    }
}
