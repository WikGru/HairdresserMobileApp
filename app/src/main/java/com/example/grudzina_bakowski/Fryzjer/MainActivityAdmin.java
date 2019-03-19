package com.example.grudzina_bakowski.Fryzjer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.grudzina_bakowski.Fryzjer.Managers.AdminTabsPageAdapter;
import com.example.grudzina_bakowski.Fryzjer.MoreButtonViewActivities.MoreButtonAboutUsActivity;
import com.example.grudzina_bakowski.Fryzjer.MoreButtonViewActivities.MoreButtonOpcjeActivityAdmin;

public class MainActivityAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.tabPanel_admin);
        AdminTabsPageAdapter adminTabsPageAdapter = new AdminTabsPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        final ViewPager viewPager = findViewById(R.id.viewPager_admin);
        viewPager.setAdapter(adminTabsPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));// dodanie listenera do zmiany layoutow

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition()); // wstawia do viewpager aktualny wybraną zakładke
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_opcje:
                Intent intentOpcje = new Intent(this, MoreButtonOpcjeActivityAdmin.class);
                startActivity(intentOpcje);
                return true;
            case R.id.action_about_us:
                Intent intentAboutUs = new Intent(this, MoreButtonAboutUsActivity.class);
                startActivity(intentAboutUs);
                return true;
//            case R.id.action_portfolio:
//                Intent intentPortfolio = new Intent(this, MoreButtonPortfolioActivity.class);
//                startActivity(intentPortfolio);
//                return true;
        }
        return false;
    }


}
