package com.example.grudzina_bakowski.Fryzjer.TabViewADMIN;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.grudzina_bakowski.Fryzjer.Entity.Price;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;
import com.example.grudzina_bakowski.Fryzjer.WidgetTemplates.ExpandableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AdminPriceList extends Fragment {
    private Button bCreateNewService;
    private View myFragView;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //te linijki są bardzo potrzebne, bez nich nie działa
        //myFragView pozwala przechwycić context z mainActivity
        myFragView = inflater.inflate(R.layout.fragment_admin_pricelist_edit, container, false);

        bCreateNewService = myFragView.findViewById(R.id.buttonAddNewService);
        bCreateNewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editServiceData = new Intent(myFragView.getContext(), AdminPriceListEdit.class);
                startActivity(editServiceData);
            }
        });




        return myFragView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            listDataHeader = new ArrayList<>();
            listDataChild = new HashMap<>();
            List<String> activeHeader;

            SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());

            List<Price> priceList = sqLiteCRUD.getAllPrices();

            String hService;
            String hShort = "";
            String hMedium = "";
            String hLong = "";
            String serviceTime = "";

            //Filter priceList
            for (Price price : priceList) {
                hService = price.getServiceName();
                hShort = price.gethShort();
                hMedium = price.gethMedium();
                hLong = price.gethLong();
                serviceTime = price.getTime();

                activeHeader = new ArrayList<>();
                //Check and add if suffix nesessery
                if (!hShort.contains("X")) hShort += "zł";
                if (!hMedium.contains("X")) hMedium += "zł";
                if (!hLong.contains("X")) hLong += "zł";
                if (!serviceTime.contains("X")) serviceTime += "min";

                //Add children to activeHeader
                activeHeader.add("Włosy krótkie;" + hShort);
                activeHeader.add("Włosy średnie;" + hMedium);
                activeHeader.add("Włosy długie;" + hLong);
                activeHeader.add("Czas trwania usługi;" + serviceTime);

                //Add active header to headerList
                listDataHeader.add(hService);

                //Integrate list
                listDataChild.put(hService, activeHeader); // Header, Child date
            }

            expListView = myFragView.findViewById(R.id.lvExp);


            //Redirect to modify pricelistItem fragment
            expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    long packedPosition = expListView.getExpandableListPosition(position);

                    int itemType = ExpandableListView.getPackedPositionType(packedPosition);
                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);

                    //Trigger only when clicked on GROUP
                    if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                        Intent intent = new Intent(myFragView.getContext(), AdminPriceListEdit.class);
                        intent.putExtra("serviceName", parent.getItemAtPosition(position).toString());
                        intent.putExtra("hShort", expListView.getExpandableListAdapter().getChild(groupPosition, 0).toString().split(";")[1].replaceAll("zł", ""));
                        intent.putExtra("hMedium", expListView.getExpandableListAdapter().getChild(groupPosition, 1).toString().split(";")[1].replaceAll("zł", ""));
                        intent.putExtra("hLong", expListView.getExpandableListAdapter().getChild(groupPosition, 2).toString().split(";")[1].replaceAll("zł", ""));
                        intent.putExtra("serviceTime", expListView.getExpandableListAdapter().getChild(groupPosition, 3).toString().split(";")[1].replaceAll("min", ""));
                        startActivity(intent);
                    }
                    return true;
                }
            });


            ExpandableListAdapter listAdapter = new ExpandableList(myFragView.getContext(), listDataHeader, listDataChild);
            expListView.setAdapter(listAdapter);

        }


    }
}

