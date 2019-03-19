package com.example.grudzina_bakowski.Fryzjer.VisitUserViewActivities;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.grudzina_bakowski.Fryzjer.Entity.MojeWizyty;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VisitUserHistoriaWizytCalendar extends Fragment {
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View myFragView = inflater.inflate(R.layout.fragment_mojewizyty_calendar, container, false);
        final ArrayList<MojeWizyty> strings = new ArrayList<>();

        final SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(getContext());

        List<MojeWizyty> myVisits = sqLiteCRUD.getAllMojeWizyty();

        Calendar calendar;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        String strDate = sdf.format(todayDate);
        int todayYear = Integer.valueOf(strDate.substring(0, 4));
        int todayMonth = Integer.valueOf(strDate.substring(5, 7));
        int todayDay = Integer.valueOf(strDate.substring(8, 10));

        for (MojeWizyty visit : myVisits) {
            int year = Integer.valueOf(visit.getDate().substring(0, 4));
            int month = Integer.valueOf(visit.getDate().substring(5, 7));
            int day = Integer.valueOf(visit.getDate().substring(8, 10));
            if (year < todayYear ||
                    (year == todayYear && month < todayMonth) ||
                    (year == todayYear && month == todayMonth && day < todayDay)) {
                strings.add(visit);
            }
        }


        listView = myFragView.findViewById(R.id.ListView_mojewizyty);

        AdapterHistoriaWizyt adapter = new AdapterHistoriaWizyt(myFragView.getContext(), R.layout.fragment_mojewizyty, strings);

        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                List<MojeWizyty> myVisits = sqLiteCRUD.getAllMojeWizyty();

                strings.clear();
                Calendar calendar;
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                calendar = Calendar.getInstance();
                Date todayDate = calendar.getTime();
                String strDate = sdf.format(todayDate);
                int todayYear = Integer.valueOf(strDate.substring(0, 4));
                int todayMonth = Integer.valueOf(strDate.substring(5, 7));
                int todayDay = Integer.valueOf(strDate.substring(8, 10));

                for (MojeWizyty visit : myVisits) {
                    int year = Integer.valueOf(visit.getDate().substring(0, 4));
                    int month = Integer.valueOf(visit.getDate().substring(5, 7));
                    int day = Integer.valueOf(visit.getDate().substring(8, 10));
                    if (year < todayYear ||
                            (year == todayYear && month < todayMonth) ||
                            (year == todayYear && month == todayMonth && day < todayDay)) {
                        strings.add(visit);
                    }
                }
            }
        });
        listView.setAdapter(adapter);
        return myFragView;
    }


}

class AdapterHistoriaWizyt extends ArrayAdapter {

    public int layout;
    List<MojeWizyty> wizyty_obiekts;

    public AdapterHistoriaWizyt(Context context, int resource, List<MojeWizyty> objects) {
        super(context, resource, objects);
        layout = resource;
        wizyty_obiekts = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(layout, parent, false);
            ViewholderHistoriaWizyt viewholder = new ViewholderHistoriaWizyt();
            viewholder.data = convertView.findViewById(R.id.data_mojewizyt);
            viewholder.godzina = convertView.findViewById(R.id.godzina_mojewizyt);
            viewholder.rodzaj = convertView.findViewById(R.id.rodzaj_mojewizyty);
            MojeWizyty obiekt = (MojeWizyty) getItem(position);

            viewholder.data.setText(obiekt.getDate());
            viewholder.godzina.setText(obiekt.getTime());
            viewholder.rodzaj.setText(obiekt.getService());

            convertView.setTag(viewholder);
        }

        return convertView;
    }
}

class ViewholderHistoriaWizyt {
    TextView rodzaj;
    TextView data;
    TextView godzina;
}
