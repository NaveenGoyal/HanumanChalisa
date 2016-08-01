package com.vgnarray.hanumanchalisa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyArtiAdapter extends FragmentPagerAdapter {

    public MyArtiAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 6;
    }


    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        Fragment fragment = new ArtiFragment();
        Bundle args = new Bundle();
        args.putInt(ArtiFragment.ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub

        switch (position) {
            case 0:
                return "श्री हनुमान चालीसा ";
            case 1:
                return "आरती";
            case 2:
                return "श्री बजरंग बाण";
            case 3:
                return "संकटमोचन पाठ";
            case 4:
                return "मंत्र";
            case 5:
                return "कथा";
        }
        return super.getPageTitle(position);
    }

}
