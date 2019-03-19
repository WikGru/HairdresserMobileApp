package com.example.grudzina_bakowski.Fryzjer.UmowWizyteFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.Entity.Price;
import com.example.grudzina_bakowski.Fryzjer.Entity.UmowWizyteEncja;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;
import com.example.grudzina_bakowski.Fryzjer.VisitUserViewActivities.VisitUserUmowWizyteActivity;

import java.util.ArrayList;
import java.util.List;


public class UmowWizyteServiceSelect extends Fragment {
    Button nextButton;
    LinearLayout linear;
    List<Switch> switchList = new ArrayList<>();
    SQLiteCRUD sqLiteCRUD;

    private ConnectionName connectionName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View myFragView = inflater.inflate(R.layout.fragment_umow_wizyte_service_select, container, false);
        linear = myFragView.findViewById(R.id.switchBoxLayout);
        nextButton = myFragView.findViewById(R.id.nextButton);

        sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());

        List<Price> priceList = sqLiteCRUD.getAllPrices();

        //Uzupełnij widok switchami dla każdej usługi
        for (Price price : priceList) {
            if (price.getTime().contains("X")) {
                System.out.println("HIDDEN:\t" + price.getServiceName());
                continue;
            }

            LayoutInflater infalInflater = (LayoutInflater) myFragView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = infalInflater.inflate(R.layout.switch_layout, null);
            final Switch aSwitch = convertView.findViewById(R.id.sw_dynamic);
            aSwitch.setText(price.getServiceName());

            switchList.add(aSwitch);
            linear.addView(convertView);


            //Swiping as Click
            aSwitch.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return event.getActionMasked() == MotionEvent.ACTION_MOVE;
                }
            });

            //OnClick let only one switch to be selected
            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Switch x : switchList) {
                        if (x.isChecked()) {
                            x.setChecked(false);
                        }
                    }
                    aSwitch.setChecked(true);
                }
            });
        }

        //Find which switch isChecked
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedService = "";
                for (Switch x : switchList) {
                    if (x.isChecked()) {
                        selectedService += x.getText();
                    }
                }

                //Compare to find selected service in PriceList
                List<Price> priceList = sqLiteCRUD.getAllPrices();
                String servicePriceShort = "";
                String servicePriceMedium = "";
                String servicePriceLong = "";
                String priceRange;
                for (Price x : priceList) {
                    if (x.getServiceName().equals(selectedService)) {
                        servicePriceShort = x.gethShort();
                        servicePriceMedium = x.gethMedium();
                        servicePriceLong = x.gethLong();
                    }
                }

                if (servicePriceShort.contains("X") && servicePriceMedium.contains("X")) {
                    priceRange = servicePriceLong + "zł";
                } else if (servicePriceShort.contains("X")) {
                    priceRange = servicePriceMedium + '-' + servicePriceLong + "zł";
                } else {
                    priceRange = servicePriceShort + '-' + servicePriceLong + "zł";
                }


                //Set singleton values
                UmowWizyteEncja umowWizyteEncja = UmowWizyteEncja.getUmowWizyteEncja(); //Singleton
                // SELECTED SERVICE
                umowWizyteEncja.setService(selectedService);
                // PREDICTED PRICE_RANGE
                umowWizyteEncja.setPriceRange(priceRange);


//                int timeGodzina = Integer.valueOf(UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg().split(":")[0]);
//                int timeMinuta = Integer.valueOf(UmowWizyteEncja.getUmowWizyteEncja().getTimeBeg().split(":")[1]);
                int timeDuration = 0;
                for (Price price : priceList) {
                    if (umowWizyteEncja.getService().equals(price.getServiceName())) {
                        timeDuration = Integer.valueOf(price.getTime());
                        break;
                    }
                }


                //Jump to next fragment
                ((VisitUserUmowWizyteActivity) getActivity()).GoToDateSelect();
            }
        });


        return myFragView;
    }


}
