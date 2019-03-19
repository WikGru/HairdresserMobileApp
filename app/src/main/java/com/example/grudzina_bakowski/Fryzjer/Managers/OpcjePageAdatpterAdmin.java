package com.example.grudzina_bakowski.Fryzjer.Managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grudzina_bakowski.Fryzjer.OpcjeFragments.LogOutAdmin;
import com.example.grudzina_bakowski.Fryzjer.OpcjeFragments.MainButtonsFragmentAdmin;


public class OpcjePageAdatpterAdmin extends FragmentPagerAdapter {

    private int number_tab_items;

    public OpcjePageAdatpterAdmin(FragmentManager fm, int number_tabs) {
        super(fm);
        this.number_tab_items = number_tabs;
    }

    @Override
    public Fragment getItem(int select) {
        switch (select) {
            case 0:
                return new MainButtonsFragmentAdmin();
            case 1:
                return new LogOutAdmin();
            default:
                return new MainButtonsFragmentAdmin(); // musi zostać bo nullpointer...

        }
    }

    @Override
    public int getCount() {
        return number_tab_items;
    }
}
