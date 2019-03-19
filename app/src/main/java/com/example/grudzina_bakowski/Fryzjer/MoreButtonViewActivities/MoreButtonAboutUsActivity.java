package com.example.grudzina_bakowski.Fryzjer.MoreButtonViewActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.grudzina_bakowski.Fryzjer.R;

public class MoreButtonAboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_button_about_us);

        Toolbar toolbar = findViewById(R.id.toolbarAboutus);
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
