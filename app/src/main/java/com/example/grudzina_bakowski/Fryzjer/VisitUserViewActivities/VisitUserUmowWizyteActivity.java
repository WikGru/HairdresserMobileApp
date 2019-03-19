package com.example.grudzina_bakowski.Fryzjer.VisitUserViewActivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.grudzina_bakowski.Fryzjer.Managers.UmowWizytePageAdapter;
import com.example.grudzina_bakowski.Fryzjer.R;

public class VisitUserUmowWizyteActivity extends AppCompatActivity {


    UmowWizytePageAdapter umowWizytePageAdapter;
    ViewPager setVisitPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_user_umow_wizyte);


        setVisitPageAdapter = this.findViewById(R.id.setVisitViewPager);
        umowWizytePageAdapter = new UmowWizytePageAdapter(getSupportFragmentManager(), 3); // przekazuje liczbe tabitem i  Fragmentmanager do interkacji z wybranym fragmentem
        setVisitPageAdapter.setAdapter(umowWizytePageAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void GoToDateSelect() {
        setVisitPageAdapter.setCurrentItem(1);
    }

    public void GoToSummary() {
        setVisitPageAdapter.setCurrentItem(2);
    }

    public void Apply(){
        setVisitPageAdapter.setCurrentItem(3);
    }
}

