package com.example.grudzina_bakowski.Fryzjer.Managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grudzina_bakowski.Fryzjer.OpcjeFragments.LogInAsAdmin;
import com.example.grudzina_bakowski.Fryzjer.TabViewFragments.TabPriceList;
import com.example.grudzina_bakowski.Fryzjer.TabViewFragments.TabVisitUser;

public class MainTabsPageAdapter extends FragmentPagerAdapter {
    private int number_tab_items;

    public MainTabsPageAdapter(FragmentManager fragmentManager, int number_tabs) {
        super(fragmentManager);
        this.number_tab_items = number_tabs;
    }

    @Override
    public Fragment getItem(int select) { // sprawdza wybrana zakładke
        switch (select) {
//            case 0:
//                return new LogInAsAdmin();
            case 0:
                return new TabVisitUser();
            case 1:
                return new TabPriceList();
             default:
                return new LogInAsAdmin(); // musi zostać bo nullpointer...

        }
    }

    @Override
    public int getCount() {
        return number_tab_items;
    }
}
