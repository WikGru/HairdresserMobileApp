package com.example.grudzina_bakowski.Fryzjer.Managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grudzina_bakowski.Fryzjer.TabViewADMIN.AdminAddVisit;
import com.example.grudzina_bakowski.Fryzjer.TabViewADMIN.AdminPriceList;
import com.example.grudzina_bakowski.Fryzjer.TabViewADMIN.AdminVisits;


public class AdminTabsPageAdapter extends FragmentPagerAdapter {
    private int number_tab_items;

    public AdminTabsPageAdapter(FragmentManager fragmentManager, int number_tabs) {
        super(fragmentManager);
        this.number_tab_items = number_tabs;
    }

    @Override
    public Fragment getItem(int select) { // sprawdza wybrana zakładke
        switch (select) {
            case 0:
                return new AdminVisits();
            case 1:
                return new AdminPriceList();
            case 2:
                return new AdminAddVisit();
            default:
                return new AdminVisits();
        }
    }

    @Override
    public int getCount() {
        return number_tab_items;
    }
}
