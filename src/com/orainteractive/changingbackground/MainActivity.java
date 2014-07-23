package com.orainteractive.changingbackground;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.orainteractive.changingbackground.fragment.BaseFragment;
import com.orainteractive.changingbackground.fragment.HomeFragment;
import com.orainteractive.changingbackground.fragment.SecondFragment;

/**
 * The main Activity of the app
 */
public class MainActivity extends FragmentActivity implements BaseFragment.ButtonListener {
    private static final int FRAGMENT_CONTAINER = R.id.fragment_container;
    private static final int TRANSITION_DURATION = 2000;
    TransitionDrawable mBackgroundTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initFragments();
        mBackgroundTransition = (TransitionDrawable) findViewById(R.id.main_activity_layout).getBackground();
        showHomeFragment();
    }

    /** Initializes and adds the necessary fragments to the FragmentManager. */
    private void initFragments() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(new SecondFragment(), SecondFragment.TAG);
        transaction.add(FRAGMENT_CONTAINER, new HomeFragment(), HomeFragment.TAG);
        transaction.commit();
    }

    /** Displays the "home" fragment with the "go forward" button. */
    private void showHomeFragment() {
        replaceFragment(new HomeFragment(), HomeFragment.TAG);
    }

    /** Displays the "second" fragment with the "go back" button. */
    private void showSecondFragment() {
        replaceFragment(new SecondFragment(), SecondFragment.TAG, true);
    }

    /** Replaces the fragment container with the given fragment (by tag name). */
    private void replaceFragment(final Fragment fragment, final String tag) {
        replaceFragment(fragment, tag, false);
    }

    /** Replaces the fragment container with the given fragment (by tag name). */
    private void replaceFragment(final Fragment fragment, final String tag, final boolean animate) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Add animations to transaction if specified
        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                    R.anim.slide_in_left, R.anim.slide_out_right);
            mBackgroundTransition.startTransition(TRANSITION_DURATION);
        }

        transaction.addToBackStack(null);
        transaction.replace(FRAGMENT_CONTAINER, fragment, tag);
        transaction.commit();
    }

    /** @return true if the fragment currently shown is the only one in the stack. */
    private boolean isFirstFragment() {
        return getSupportFragmentManager().getBackStackEntryCount() < 2;
    }

    /** Called when the main button in one of the fragments is pressed. */
    @Override
    public void onClick() {
        if (isFirstFragment()) {
            showSecondFragment();
        } else {
            mBackgroundTransition.reverseTransition(TRANSITION_DURATION);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        if (!isFirstFragment()) {
            mBackgroundTransition.reverseTransition(TRANSITION_DURATION);
        }
        super.onBackPressed();
    }
}
