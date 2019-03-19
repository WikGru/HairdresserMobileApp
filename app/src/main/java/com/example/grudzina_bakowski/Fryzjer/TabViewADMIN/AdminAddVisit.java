package com.example.grudzina_bakowski.Fryzjer.TabViewADMIN;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.grudzina_bakowski.Fryzjer.Entity.Price;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;

import java.util.ArrayList;
import java.util.List;


public class AdminAddVisit extends Fragment {
    private View myFragView;
    private SQLiteCRUD sqLiteCRUD;
    private List<Price> priceList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragView = inflater.inflate(R.layout.fragment_addvisit_admin, container, false);
        sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());

        priceList = sqLiteCRUD.getAllPrices();

        List<String> serviceList = new ArrayList<>();

        for (Price price : priceList) {
            if (!price.gethMedium().contains("X")) {
                serviceList.add(price.getServiceName());
            }
        }
        Spinner spinner = myFragView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(myFragView.getContext(),
                android.R.layout.simple_spinner_item, serviceList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


//        spinner.item
        return myFragView;
    }
}
