package com.orainteractive.changingbackground;

import android.app.Activity;
import android.os.Bundle;
import com.orainteractive.changingbackground.fragment.BaseFragment;

/**
 * The main Activity of the app
 */
public class MainActivity extends Activity implements BaseFragment.ButtonListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void onClick() {
        // TODO Switch Fragments
        // TODO update background color
    }
}
