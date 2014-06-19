package com.orainteractive.changingbackground;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import com.orainteractive.changingbackground.fragment.HomeFragment;
import com.orainteractive.changingbackground.fragment.SecondFragment;

/**
 * The main Activity of the app
 */
public class MainActivity extends FragmentActivity {

    private LinearLayout mMainLayout;
    public static final int TRANSITION_DURATION = 1500;
    private FragmentManager mFragmentManager;
    private TransitionDrawable mBackgroundTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mFragmentManager = getSupportFragmentManager();
        mMainLayout = (LinearLayout) findViewById(R.id.main_layout);
        init();
    }

    /**
     * Initializes activity at first launch.
     */
    private void init(){
        setBackgroundSwitchingTransition();
        addHomeFragment();
    }

    /**
     * Adds HomeFragment to the fragment
     * container at first app launch.
     */
    private void addHomeFragment(){
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.fragmentContainer, homeFragment);
        ft.commit();
    }

    /**
     * Creates transition from drawable resources
     * and sets it as main activity layout background.
     */
    private void setBackgroundSwitchingTransition(){
        Drawable blueDrawable = getResources().getDrawable(R.drawable.blue);
        Drawable greenDrawable = getResources().getDrawable(R.drawable.green);
        mBackgroundTransition = new TransitionDrawable(new Drawable[]{blueDrawable, greenDrawable});
        mMainLayout.setBackgroundDrawable(mBackgroundTransition);
    }

    /**
     * Shows HomeFragment on the fragment container
     * and starts MainActivity background image reverse transition.
     * Called from SecondFragment.
     */
    public void showHomeFragment(){
        /* In our case, creating a new instance is not important,
        but there are cases where it is necessary to keep fragments instance.
        In this method I can pass values to the fragment that replaced        */
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        ft.replace(R.id.fragmentContainer, homeFragment);
        ft.commit();
        mBackgroundTransition.reverseTransition(TRANSITION_DURATION);
    }

    /**
     * Shows SecondFragment on the fragment container
     * and starts MainActivity background image transition.
     * Called from HomeFragment.
     */
    public void showSecondFragment(){
        Fragment secondFragment = new SecondFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.replace(R.id.fragmentContainer, secondFragment);
        ft.commit();
        mBackgroundTransition.startTransition(TRANSITION_DURATION);
    }

}
