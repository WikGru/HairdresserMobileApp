package com.example.grudzina_bakowski.Fryzjer.Managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grudzina_bakowski.Fryzjer.UmowWizyteFragments.UmowWizyteDateSelect;
import com.example.grudzina_bakowski.Fryzjer.UmowWizyteFragments.UmowWizyteServiceSelect;
import com.example.grudzina_bakowski.Fryzjer.UmowWizyteFragments.UmowWizyteSummary;

public class UmowWizytePageAdapter extends FragmentPagerAdapter {
    private int number_tab_items;

    public UmowWizytePageAdapter(FragmentManager fragmentManager, int number_tabs) {
        super(fragmentManager);
        this.number_tab_items = number_tabs;
    }

    @Override
    public Fragment getItem(int select) { // sprawdza wybrana zakładke
        switch (select) {
            case 0:
                return new UmowWizyteServiceSelect();
            case 1:
                return new UmowWizyteDateSelect();
            case 2:
                return new UmowWizyteSummary();
            default:
                return new UmowWizyteServiceSelect(); // musi zostać bo nullpointer...

        }
    }


    @Override
    public int getCount() {
        return number_tab_items;
    }
}
