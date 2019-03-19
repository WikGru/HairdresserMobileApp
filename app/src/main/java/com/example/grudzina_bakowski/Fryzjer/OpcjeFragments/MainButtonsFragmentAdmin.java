package com.example.grudzina_bakowski.Fryzjer.OpcjeFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.grudzina_bakowski.Fryzjer.R;


public class MainButtonsFragmentAdmin extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View myFragView = inflater.inflate(R.layout.fragment_opcje_buttons_admin, container, false);


        ImageButton likeFiona = myFragView.findViewById(R.id.LikeFiona);
        likeFiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Salon-Fryzjerski-Fiona-254865725218081"));
                startActivity(browserIntent);
            }
        });

        return myFragView;
    }
}
