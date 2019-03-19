package com.example.grudzina_bakowski.Fryzjer.TabViewFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.grudzina_bakowski.Fryzjer.Entity.Price;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;
import com.example.grudzina_bakowski.Fryzjer.WidgetTemplates.ExpandableList;
import com.example.grudzina_bakowski.Fryzjer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabPriceList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //te linijki są bardzo potrzebne, bez nich nie działa
        //myFragView pozwala przechwycić context z mainActivity
        View myFragView = inflater.inflate(R.layout.fragment_pricelist, container, false);

        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();
        List<String> activeHeader = new ArrayList<>();

        SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(getContext());

        List<Price> priceList = sqLiteCRUD.getAllPrices();

        String hService = "";
        String hShort = "";
        String hMedium = "";
        String hLong = "";

        //Filter priceList
        for (Price price : priceList) {
            hService = price.getServiceName();
            hShort = price.gethShort();
            hMedium = price.gethMedium();
            hLong = price.gethLong();

            activeHeader = new ArrayList<>();
            //Check and add if suffix nesessery
            if (!hShort.contains("X")) hShort += "zł";
            if (!hMedium.contains("X")) hMedium += "zł";
            if (!hLong.contains("X")) hLong += "zł";

            //Add children to activeHeader
            activeHeader.add("Włosy krótkie;" + hShort);
            activeHeader.add("Włosy średnie;" + hMedium);
            activeHeader.add("Włosy długie;" + hLong);

            //Add active header to headerList
            listDataHeader.add(hService);

            //Integrate list
            listDataChild.put(hService, activeHeader); // Header, Child data
        }

        //Create expandable list
        ExpandableListView expListView = myFragView.findViewById(R.id.lvExp);
        ExpandableListAdapter listAdapter = new ExpandableList(myFragView.getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);


        return myFragView;

    }
}

