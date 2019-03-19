package com.example.grudzina_bakowski.Fryzjer.MoreButtonViewActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import com.example.grudzina_bakowski.Fryzjer.OpcjeFragments.ImageAdapter;
import com.example.grudzina_bakowski.Fryzjer.R;

public class MoreButtonPortfolioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_button_portfolio);

        GridView gridView =(GridView) findViewById(R.id.gridportfolio);
        gridView.setAdapter(new ImageAdapter(getApplicationContext()));

        Toolbar toolbar = findViewById(R.id.toolbarPortfolio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



    }
}
