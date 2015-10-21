package com.vgnarray.hanumanchalisa.hanumanchalisa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.vgnarray.hanumanchalisa.adapter.MyArtiAdapter;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	MyArtiAdapter myArtiAdapter;
	ViewPager myPager;
	Toolbar toolbar;
	TabLayout tabLayout;
	FloatingActionButton fab_aarti, fab_chalisa;
	TextView txt_aarti, txt_chalisa;

	AnimationSet animation;
	int fadeInDuration = 1000;
	int timeBetween = 2000;
	int fadeOutDuration = 1000;

	MediaPlayer mPlayer1, mPlayer2;

	private int num = 0;
	private int count = 0;
	private int length = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		myPager = (ViewPager) findViewById(R.id.pager);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mPlayer1 = MediaPlayer.create(getApplicationContext(),
				R.raw.aarti);

		mPlayer2 = MediaPlayer.create(getApplicationContext(),
				R.raw.chalisa);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
		fab_aarti = (FloatingActionButton) findViewById(R.id.fab2);
		fab_chalisa =(FloatingActionButton) findViewById(R.id.fab3);
		txt_aarti = (TextView) findViewById(R.id.txtView2);
		txt_chalisa =(TextView) findViewById(R.id.txtView3);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				initAnimation();
				if(fab_aarti.getVisibility() == View.GONE || fab_chalisa.getVisibility() == View.GONE){
					handleVisibility(true);
					handleAnimation(animation);
				} else{
					handleVisibility(false);
					handleAnimation(null);
				}

			}
		});

		fab_aarti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				count = num % 2;


				switch (count) {
					case 0:
						Thread thread = new Thread(new Runnable() {
							public void run() {
								if(!mPlayer2.isPlaying()) {
									Log.d("Naveen", "Case 0 Inside the If Of On Click of MPlayer1");
									mPlayer1.seekTo(length);
									mPlayer1.start();
								} else {
									Log.d("Naveen", "Case 0 Inside the else Of On Click of MPlayer1");
									mPlayer1.seekTo(length);
									mPlayer1.start();
									mPlayer2.stop();

								}
							}
						});
						thread.start();

						fab_aarti.setImageDrawable(getResources().getDrawable(R.drawable.pause_1));

						//fabImageButton.setImageDrawable(R.drawable.action_pause);
						num++;
						break;
					case 1:

						if (mPlayer1 != null && mPlayer1.isPlaying()) {
							Log.d("Naveen", "Case 1 Inside the else Of On Click of MPlayer1");
							mPlayer1.pause();
							length = mPlayer1.getCurrentPosition();
						}
						//fabImageButton.setBackgroundResource(R.drawable.action_play);
						fab_aarti.setImageDrawable(getResources().getDrawable(R.drawable.play_1));

						num++;

						break;
				}

				txt_aarti.setVisibility(View.GONE);
				txt_chalisa.setVisibility(View.GONE);

			}
		});
		fab_chalisa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				count = num % 2;

				switch (count) {
					case 0:
						Thread thread = new Thread(new Runnable() {
							public void run() {
								if(!mPlayer1.isPlaying()) {
									Log.d("Naveen", " Case 0 Inside the if Of On Click of MPlayer2");
									mPlayer2.seekTo(length);
									mPlayer2.start();
								} else {
									Log.d("Naveen", "Case 0 Inside the else Of On Click of MPlayer2");
									mPlayer2.seekTo(length);
									mPlayer2.start();
									mPlayer1.stop();

								}
							}
						});
						thread.start();

						fab_chalisa.setImageDrawable(getResources().getDrawable(R.drawable.pause_1));

						//fabImageButton.setImageDrawable(R.drawable.action_pause);
						num++;
						break;
					case 1:

						if (mPlayer2 != null && mPlayer2.isPlaying()) {
							Log.d("Naveen", "Case 1 Inside the else Of On Click of MPlayer2");
							mPlayer2.pause();
							length = mPlayer2.getCurrentPosition();
						}
						//fabImageButton.setBackgroundResource(R.drawable.action_play);
						fab_chalisa.setImageDrawable(getResources().getDrawable(R.drawable.play_1));

						num++;

						break;
				}
				//fab_chalisa.setImageDrawable(getResources().getDrawable(R.drawable.pause_1));
				txt_chalisa.setVisibility(View.GONE);
				txt_aarti.setVisibility(View.GONE);
		}
		});

		myArtiAdapter = new MyArtiAdapter(getSupportFragmentManager());
		myPager.setAdapter(myArtiAdapter);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		myPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
				tabLayout));

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_1) {
			myPager.setCurrentItem(0); ;
		} else if (id == R.id.nav_2) {
			myPager.setCurrentItem(1);
		} else if (id == R.id.nav_3) {
			myPager.setCurrentItem(2);
		} else if (id == R.id.nav_4) {
			myPager.setCurrentItem(3);
		} else if (id == R.id.nav_5) {
			myPager.setCurrentItem(4);
		} else if (id == R.id.nav_6) {
			myPager.setCurrentItem(5);
		} else if (id == R.id.nav_share) {
			shareIntent();
		} else if (id == R.id.nav_about_us) {
			alertViewAbout("Powered by VGNarr@y Soln. \n\nMail @ vgnary@gmail.com");

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}


	private void alertViewAbout(String message ) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setTitle("About US")
				.setMessage(message)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
						dialoginterface.cancel();
					}
				}).show();
	}

	private void shareIntent(){
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT,
				"Hey check out my app at: https://play.google.com/store/apps/details?id=com.vgnary.srihanuman");
		sendIntent.setType("text/plain");
		startActivity(sendIntent);
	}


	public void handleVisibility( boolean bool){

		int state ;
		if(bool) {
			fab_aarti.setImageDrawable(getResources().getDrawable(R.drawable.play_1));
			fab_chalisa.setImageDrawable(getResources().getDrawable(R.drawable.play_1));
			state = View.VISIBLE;
		}else{
			fab_aarti.setImageDrawable(getResources().getDrawable(R.drawable.play_1));
			fab_chalisa.setImageDrawable(getResources().getDrawable(R.drawable.play_1));
			state = View.GONE;
		}
		txt_aarti.setVisibility(state);
		txt_chalisa.setVisibility(state);

		fab_aarti.setVisibility(state);
		fab_chalisa.setVisibility(state);



	}

	public void handleAnimation(AnimationSet anim){
		fab_aarti.setAnimation(anim);
		fab_chalisa.setAnimation(anim);

	}

	public void initAnimation() {
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(fadeInDuration);
		animation = new AnimationSet(false);
		animation.addAnimation(fadeIn);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPlayer1 != null && mPlayer2 != null) {
			mPlayer1.stop();
			mPlayer2.stop();
			mPlayer1 = null;
			mPlayer2 = null;
		}
	}

}
