package com.example.grudzina_bakowski.Fryzjer.Managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grudzina_bakowski.Fryzjer.OpcjeFragments.LogInAsAdmin;
import com.example.grudzina_bakowski.Fryzjer.OpcjeFragments.MainButtonsFragment;


public class OpcjePageAdatpter extends FragmentPagerAdapter {

    private int number_tab_items;

    public OpcjePageAdatpter(FragmentManager fm, int number_tabs) {
        super(fm);
        this.number_tab_items = number_tabs;
    }

    @Override
    public Fragment getItem(int select) {
        switch (select) {
            case 0:

                return new MainButtonsFragment();
            case 1:
                return new LogInAsAdmin();
            default:
                return new MainButtonsFragment(); // musi zostać bo nullpointer...

        }
    }

    @Override
    public int getCount() {
        return number_tab_items;
    }
}
