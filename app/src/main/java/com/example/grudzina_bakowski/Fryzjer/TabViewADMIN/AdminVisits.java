package com.example.grudzina_bakowski.Fryzjer.TabViewADMIN;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionToDatabase;
import com.example.grudzina_bakowski.Fryzjer.Entity.Visit;
import com.example.grudzina_bakowski.Fryzjer.HandlerHTTP.DeleteData;
import com.example.grudzina_bakowski.Fryzjer.HandlerHTTP.HTTPDateHandler;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminVisits extends Fragment {

    private ListView listView;
    private View myFragView;
    private CalendarView calendarView;
    private String data;
    private ArrayList<Visit> strings = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragView = inflater.inflate(R.layout.fragment_admin_visits, container, false);

        calendarView = myFragView.findViewById(R.id.calendarViewAdmin);
        listView = myFragView.findViewById(R.id.Listview_admin);

        SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());

//        ArrayList<Wizyty_Obiekt> strings = new ArrayList<>();

        final List<Visit> visitList = sqLiteCRUD.getAllVisits();

        listView.setAdapter(new AdapterVisits(myFragView.getContext(), R.layout.listitem_layout, strings));


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                String mon;
                if (month < 10) {
                    mon = "0" + month;
                } else {
                    mon = String.valueOf(month);
                }
                String day;
                if(dayOfMonth < 10){
                    day = "0" + dayOfMonth;
                }else{
                    day = String.valueOf(dayOfMonth);
                }
                data = year + "-" + mon + "-" + day;

                //Update visible visits on screen
                strings.clear();
                for (Visit visit : visitList) {
                    if (visit.getDate().equals(data)) {
                        strings.add(visit);

                    }
                    System.out.println("DATE: " + data + '\t' + visit.getDate());
                }
                listView.setAdapter(new AdapterVisits(myFragView.getContext(), R.layout.listitem_layout, strings));
            }
        });


        return myFragView;
    }


}

class AdapterVisits extends ArrayAdapter {

    public int layout;
    List<Visit> visitObjs;

    public AdapterVisits(Context context, int resource, List<Visit> objects) {
        super(context, resource, objects);
        layout = resource;
        visitObjs = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(layout, parent, false);
            Viewholder viewholder = new Viewholder();
            viewholder.name = convertView.findViewById(R.id.imie_admin);
            viewholder.time = convertView.findViewById(R.id.godzina_admin);
            viewholder.service = convertView.findViewById(R.id.usluga_admin);
            final Visit visit = (Visit) getItem(position);

            viewholder.name.setText(visit.getName());
            viewholder.time.setText(visit.getTimeBeg());
            viewholder.service.setText(visit.getService());

            viewholder.button = convertView.findViewById(R.id.button_admin_usun);
            viewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectionName connectionName;
                    connectionName = new ConnectionName();

                    String test = "test";
                    String janosik = "";
                    HTTPDateHandler httpDateHandler = new HTTPDateHandler();
                    ConnectionToDatabase connectionToDatabase = new ConnectionToDatabase();
                    JSONArray response  = httpDateHandler.GetHTTPData(connectionToDatabase.getAddressAPI(connectionName.getCOLLECTION_NAME_USUN_WIZYTE()));
                    try {
                        janosik = new JSONObject().put("imie", test).toString();
                        new DeleteData(connectionName.getCOLLECTION_NAME_USUN_WIZYTE(), janosik).execute();
                        System.out.println(janosik);
                        System.out.println("DELETED:\t" + visit.getDate() + "\t" + visit.getName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            convertView.setTag(viewholder);
        }

        return convertView;
    }
}

class Viewholder {
    Button button;
    TextView name;
    TextView time;
    TextView service;

}
