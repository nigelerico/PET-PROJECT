package com.skripsi.nigel.esvira.Adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skripsi.nigel.esvira.Fragment.FragmentAdminHistoryCompleted;
import com.skripsi.nigel.esvira.Fragment.FragmentAdminHistoryInprogress;


public class PageAdapterAdmin extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterAdmin(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentAdminHistoryInprogress tab1 = new FragmentAdminHistoryInprogress();
                return tab1;
            case 1:
                FragmentAdminHistoryCompleted tab2 = new FragmentAdminHistoryCompleted();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
