package com.example.grudzina_bakowski.Fryzjer.SplashScreen;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.grudzina_bakowski.Fryzjer.R;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Objects.requireNonNull(getSupportActionBar()).hide();
            } catch (Exception ex) {
            }
        }

            load_app();
    }

    private void load_app() { // timer który po odliczeniu czasu wywoła Main Activity,
        new MongoToSQLite(getApplicationContext()).execute();
    }
}
