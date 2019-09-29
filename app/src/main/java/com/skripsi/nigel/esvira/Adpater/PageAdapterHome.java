package com.skripsi.nigel.esvira.Adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skripsi.nigel.esvira.AllFragment;
import com.skripsi.nigel.esvira.SdFragment;
import com.skripsi.nigel.esvira.SmaFragment;
import com.skripsi.nigel.esvira.SmpFragment;
import com.skripsi.nigel.esvira.UmumFragment;

public class PageAdapterHome extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterHome(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllFragment tab0 = new AllFragment();
                return tab0;
            case 1:
                SdFragment tab1 = new SdFragment();
                return tab1;
            case 2:
                SmpFragment tab2 = new SmpFragment();
                return tab2;
            case 3:
                SmaFragment tab3 = new SmaFragment();
                return tab3;
            case 4:
                UmumFragment tab4 = new UmumFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}


