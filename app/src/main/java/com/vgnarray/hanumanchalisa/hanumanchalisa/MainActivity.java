package com.vgnarray.hanumanchalisa.hanumanchalisa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.vgnarray.hanumanchalisa.adapter.MyArtiAdapter;

public class MainActivity extends AppCompatActivity {

	MyArtiAdapter myArtiAdapter;
	ViewPager myPager;

	// ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		myPager = (ViewPager) findViewById(R.id.pager);

		// actionBar = getSupportActionBar();

		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		myArtiAdapter = new MyArtiAdapter(getSupportFragmentManager());
		myPager.setAdapter(myArtiAdapter);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		myPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
				tabLayout));


		tabLayout
				.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
					@Override
					public void onTabSelected(TabLayout.Tab tab) {
						myPager.setCurrentItem(tab.getPosition());
					}

					@Override
					public void onTabUnselected(TabLayout.Tab tab) {

					}

					@Override
					public void onTabReselected(TabLayout.Tab tab) {

					}
				});
		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < myArtiAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter.
			// Also specify this Activity object, which implements the
			// TabListener interface, as the
			// listener for when this tab is selected.
			tabLayout.addTab(tabLayout.newTab().setText(
					myArtiAdapter.getPageTitle(i)));
		}

	}

}
