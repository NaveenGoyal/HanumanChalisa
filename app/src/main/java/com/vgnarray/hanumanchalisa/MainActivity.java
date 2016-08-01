package com.vgnarray.hanumanchalisa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vgnarray.hanumanchalisa.hanumanchalisa.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PlaySongIntface {
    static final boolean DEBUG = false;
    MyArtiAdapter myArtiAdapter;
    AnimationSet animation;
    String pkgName;
    MediaPlayer mPlayerArti, mPlayerChalisa, play_Om, play_Sankh, play_bell;
    private ViewPager myPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CustomFab fab_aarti, fab_chalisa;
    private ImageView img_aarti, img_chalisa;
    private RelativeLayout relativeLayout;
    private int fadeInDuration = 600;
    private TextView mTextView, mTextView2;
    private PopupWindow pwindo;
    private PlaySongIntface mSongcontext;
    ActionBarDrawerToggle toggle;
    String titleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        myPager = (ViewPager) findViewById(R.id.pager);
        mSongcontext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab_aarti = (CustomFab) findViewById(R.id.fab_arti);
        fab_chalisa = (CustomFab) findViewById(R.id.fab_chalisa);
        img_aarti = (ImageView) findViewById(R.id.imgView2);
        img_chalisa = (ImageView) findViewById(R.id.imgView3);
        play_Om = MediaPlayer.create(this, R.raw.omchant);
        play_bell = MediaPlayer.create(this, R.raw.bell);
        play_Sankh = MediaPlayer.create(this, R.raw.sankh);
        relativeLayout = (RelativeLayout) findViewById(R.id.music_controller);
        pkgName = getPackageName();

        //TelephonY state Listener
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyMgr.listen(new TeleListener(),
                PhoneStateListener.LISTEN_CALL_STATE);

        /*AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);*/


        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (!(fab_aarti.getVisibility() == View.GONE)) {
                    handleVisibility(false);
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorNull));
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAnimation();
                if (fab_aarti.getVisibility() == View.GONE || fab_chalisa.getVisibility() == View.GONE) {
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                    handleVisibility(true);
                    handleAnimation(animation);
                } else {
                    handleVisibility(false);
                    handleAnimation(null);
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorNull));
                }

            }
        });

        fab_aarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_aarti.setmSonglistener(mSongcontext);
                fab_aarti.setSelected(!fab_aarti.isSelected());
                if (fab_chalisa.isSelected()) {
                    fab_chalisa.setSelected(false);
                    mPlayerChalisa.stop();
                }
            }
        });
        fab_chalisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_chalisa.setmSonglistener(mSongcontext);
                fab_chalisa.setSelected(!fab_chalisa.isSelected());
                if (fab_aarti.isSelected()) {
                    fab_aarti.setSelected(false);
                    mPlayerArti.stop();
                }
            }
        });

        myArtiAdapter = new MyArtiAdapter(getSupportFragmentManager());
        myPager.setAdapter(myArtiAdapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        myPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                NavigationView nv = (NavigationView) findViewById(R.id.nav_view);


                switch (myPager.getCurrentItem()) {
                    case 0:
                        nv.getMenu().findItem(R.id.nav_1).setChecked(true);
                        break;
                    case 1:
                        nv.getMenu().findItem(R.id.nav_2).setChecked(true);
                        break;
                    case 2:
                        nv.getMenu().findItem(R.id.nav_3).setChecked(true);
                        break;
                    case 3:
                        nv.getMenu().findItem(R.id.nav_4).setChecked(true);
                        break;
                    case 4:
                        nv.getMenu().findItem(R.id.nav_5).setChecked(true);
                        break;
                    case 5:
                        nv.getMenu().findItem(R.id.nav_6).setChecked(true);
                        break;

                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myPager.setCurrentItem(tab.getPosition());
                getSupportActionBar().setTitle(tab.getText());
                titleString = getSupportActionBar().getTitle().toString();
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

   /* @Override
    public void onAudioFocusChange(int focusChange) {

        Toast.makeText(MainActivity.this, "Audio Focus called" +focusChange, Toast.LENGTH_SHORT).show();
        switch(focusChange){



            case AudioManager.AUDIOFOCUS_LOSS:

                Toast.makeText(MainActivity.this, "Audio FOcus is Lost", Toast.LENGTH_SHORT).show();
                // Lost focus for an unbounded amount of time: stop playback and release media player
                if (fab_aarti.isSelected()) {
                    fab_aarti.setSelected(false);
                    mPlayerArti.stop();
                }
                if(fab_chalisa.isSelected()){
                    fab_chalisa.setSelected(false);
                    mPlayerChalisa.stop();
                }
                break;


        }
    }*/

    class TeleListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    // CALL_STATE_IDLE;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // CALL_STATE_OFFHOOK;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // CALL_STATE_RINGING

                    if (fab_chalisa.isSelected()) {
                        fab_chalisa.setSelected(false);
                        mPlayerChalisa.stop();
                    }
                    if (fab_aarti.isSelected()) {
                        fab_aarti.setSelected(false);
                        mPlayerArti.stop();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (pwindo != null && pwindo.isShowing()) {
            pwindo.dismiss();
            getSupportActionBar().setTitle(titleString);
            toggle.setDrawerIndicatorEnabled(true);

        } else if (fab_aarti != null && fab_aarti.getVisibility() == View.VISIBLE) {
            handleVisibility(false);
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorNull));

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getSupportActionBar().setTitle(titleString);
        if (pwindo != null && pwindo.isShowing()) {
            pwindo.dismiss();
            showSunderPopupWindow();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_om) {
            play_Om.start();
            return true;
        }
        if (id == R.id.action_bell) {
            play_bell.start();
            return true;
        }
        if (id == R.id.action_sankh) {
            play_Sankh.start();
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
            myPager.setCurrentItem(0);
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
        } else if (id == R.id.nav_sunder_kand) {
            showSunderPopupWindow();
        } else if (id == R.id.nav_share) {
            shareIntent();
        } else if (id == R.id.nav_about_us) {
            alertViewAbout("Powered by VGNarr@y Soln. \n\nMail @ vgnary@gmail.com");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showSunderPopupWindow() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // for activity use context instead of getActivity()
        Display display = wm.getDefaultDisplay(); // getting the screen size of device
        Point size = new Point();
        display.getSize(size);
        int width = size.x;// - 70;  // Set your heights
        int height = size.y - toolbar.getHeight();// - 300; // set your widths
        if (DEBUG)
            Log.d("vinay", "width: " + width + "height" + height);
        initiatePopupWindow(width, height);
    }

    private void initiatePopupWindow(int width, int height) {
        try {
            LayoutInflater layoutInflater
                    = (LayoutInflater) getBaseContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.expaned_popup, (ViewGroup) findViewById(R.id.popup_window));

            toggle.setDrawerIndicatorEnabled(false);

            getSupportActionBar().setTitle("  सुन्दर काण्ड");
            mTextView = (TextView) popupView.findViewById(R.id.expandedTextView);
            mTextView2 = (TextView) popupView.findViewById(R.id.expandedTextView2);
            mTextView.setVisibility(View.VISIBLE);
            mTextView2.setVisibility(View.VISIBLE);

            pwindo = new PopupWindow(
                    popupView, width, height);
            String str1 = getResources().getString(R.string.sunder_kand1);
            String str2 = getResources().getString(R.string.sunder_kand2);
            mTextView.setText(str1);
            mTextView2.setText(str2);
            pwindo.setOutsideTouchable(false);
            pwindo.setFocusable(false);
            pwindo.setBackgroundDrawable(new ShapeDrawable());


            pwindo.showAsDropDown(toolbar, 0, 0);


        } catch (Exception e) {
        }
    }

    private void alertViewAbout(String message) {

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("About US")
                /*.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })*/
                .setView(dialogView).show();
    }

    private void shareIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey! check out this beautiful app of lord Shri Hanuman at: https://play.google.com/store/apps/details?id=" + pkgName);//com.vgnary.srihanuman");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    public void handleVisibility(boolean bool) {
        int state;
        if (bool) {
            state = View.VISIBLE;
        } else {
            state = View.GONE;
        }
        img_aarti.setVisibility(state);
        img_chalisa.setVisibility(state);

        fab_aarti.setVisibility(state);
        fab_chalisa.setVisibility(state);
    }

    public void handleAnimation(AnimationSet anim) {
        fab_aarti.setAnimation(anim);
        fab_chalisa.setAnimation(anim);
        img_aarti.setAnimation(anim);
        img_chalisa.setAnimation(anim);

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

        if (mPlayerArti != null) {
            mPlayerArti.stop();
            mPlayerArti.release();
            mPlayerArti = null;
        }
        if (mPlayerChalisa != null) {
            mPlayerChalisa.stop();
            mPlayerChalisa.release();
            mPlayerChalisa = null;
        }
        if (play_bell != null)
            play_bell.release();
        if (play_Om != null)
            play_Om.release();
        if (play_Sankh != null)
            play_Sankh.release();
    }


    @Override
    public void playCurr(CustomFab songobj, boolean isSelected) {
        if (songobj.getId() == R.id.fab_arti) {
            if (isSelected) {
                if (DEBUG)
                    Toast.makeText(getApplicationContext(), "if fab_arti", Toast.LENGTH_LONG).show();
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        if (mPlayerChalisa != null)
                            mPlayerChalisa.stop();
                        try {
                            mPlayerArti = MediaPlayer.create(getApplicationContext(),
                                    R.raw.aarti);

                            mPlayerArti.start();
                        } catch (Exception e) {

                            e.printStackTrace();

                        }

                    }
                });
                thread.start();
            } else {
                if (DEBUG)
                    Toast.makeText(getApplicationContext(), "else fab_arti", Toast.LENGTH_LONG).show();

                mPlayerArti.stop();
            }

        } else if (songobj.getId() == R.id.fab_chalisa) {
            if (isSelected) {
                if (DEBUG)
                    Toast.makeText(getApplicationContext(), "if fab_chalisa", Toast.LENGTH_LONG).show();

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        if (mPlayerArti != null)
                            mPlayerArti.stop();
                        try {
                            mPlayerChalisa = MediaPlayer.create(getApplicationContext(),
                                    R.raw.chalisa);
                            mPlayerChalisa.start();
                        } catch (Exception e) {

                        }
                    }
                });
                thread.start();
            } else {
                if (DEBUG)
                    Toast.makeText(getApplicationContext(), "else fab_chalisa", Toast.LENGTH_LONG).show();

                mPlayerChalisa.stop();
            }
        }
    }
}