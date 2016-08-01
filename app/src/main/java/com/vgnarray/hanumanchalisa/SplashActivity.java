package com.vgnarray.hanumanchalisa;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;


import com.vgnarray.hanumanchalisa.hanumanchalisa.R;


public class SplashActivity extends Activity {

    ImageView iv;
    ObjectAnimator alpha;
    AnimatorListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screen);
        iv = (ImageView) findViewById(R.id.imageview);
        listener = new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
                if (MainActivity.DEBUG)
                    Log.d("GAGAN", "onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                if (MainActivity.DEBUG)
                    Log.d("GAGAN", "onAnimationEnd");
                Intent start = new Intent();
                start.setClass(getApplicationContext(), MainActivity.class);
                startActivity(start);
                finish();

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        };

        alpha = ObjectAnimator.ofFloat(iv, "alpha", 0.0f, 0.3f, 0.4f, 0.5f, 1.0f);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        alpha.setDuration(2000);
        alpha.addListener(listener);
        alpha.start();


    }
}
