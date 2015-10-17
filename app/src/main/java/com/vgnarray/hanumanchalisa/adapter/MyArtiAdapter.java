package com.vgnarray.hanumanchalisa.adapter;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vgnarray.hanumanchalisa.fragment.ArtiFragment;

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
	
	switch(position){
	
	case 0:
		return "आरती";
		
	case 1:
		return "श्री हनुमान चालीसा ";
		
	case 2:
		return "मंत्र";
	case 3:
		return "कथा";
	case 4:
		return "सुन्दर काण्ड";
	case 5:
		return "संकटमोचन पाठ";
		
	}
	return super.getPageTitle(position);
}

}
