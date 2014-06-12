package com.orainteractive.changingbackground;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The main Activity of the app
 */
public class MainActivity extends FragmentActivity {

    private android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    private ImageView imageView;
    private TransitionDrawable transitionD;
    private boolean backEnabled = true;

    /**
     * Simple tracing
     * @param txt
     */
    private void line(String txt) {
        Log.i(MainActivity.class.getName(), txt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        transitionD = (TransitionDrawable) imageView.getDrawable();


        //Add first fragment programatically. Fragments specified in layout can't be removed.
        if (findViewById(R.id.fragment_container) != null && savedInstanceState == null) {
            FirstFragment firstFragment = new FirstFragment();
            fm.beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }


    }

    @Override
    public void onBackPressed() {
        if(backEnabled){
            super.onBackPressed();
        }

    }

    /**
     * Gets called from the first fragment
     */
    public void goNext() {
        line("--goNext--");
        SecondFragment f = new SecondFragment();
        //create a fragment transaction with the slide in and slide out animations.
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.rl_in, R.anim.rl_out, R.anim.lr_in, R.anim.lr_out);

        transaction.replace(R.id.fragment_container, f);
        transaction.addToBackStack(null);
        transaction.commit();

        backEnabled = false;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        transitionD.startTransition(1000);
                        backEnabled = true;
                    }
                });
            }
        }, 1000);

    }

    /**
     * Gets called from the second fragment.
     */
    public void goPrev() {
        line("--goPrev--");
        fm.popBackStack();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        transitionD.reverseTransition(1000);
                    }
                });
            }
        }, 1000);

    }


}