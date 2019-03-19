package com.example.grudzina_bakowski.Fryzjer.MoreButtonViewActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.grudzina_bakowski.Fryzjer.Managers.OpcjePageAdatpter;
import com.example.grudzina_bakowski.Fryzjer.R;

public class MoreButtonOpcjeActivity extends AppCompatActivity {

    OpcjePageAdatpter opcjePageAdapter;
    ViewPager setOpcjePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_button_opcje);

        Toolbar toolbar = findViewById(R.id.toolbarOpcje);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setOpcjePageAdapter = this.findViewById(R.id.opcje_page_adapter);
        opcjePageAdapter = new OpcjePageAdatpter(getSupportFragmentManager(), 3); // przekazuje liczbe tabitem i  Fragmentmanager do interkacji z wybranym fragmentem
        setOpcjePageAdapter.setAdapter(opcjePageAdapter);






        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }


    public void OnClickLogIn(View view){
        setOpcjePageAdapter.setCurrentItem(1);
    }
}
