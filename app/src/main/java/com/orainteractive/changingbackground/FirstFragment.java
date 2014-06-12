package com.orainteractive.changingbackground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * The main Activity of the app
 */
public class FirstFragment extends AnimatedFragment {

    // create the layout.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    public void onResume() {
        Button nextBt = (Button) getView().findViewById(R.id.trans_bt);
        //reference to the main activity
        final MainActivity main = (MainActivity) getActivity();

        //this isn't going to be another activity though.
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.goNext();
//                overridePendingTransition(R.anim.rl_in, R.anim.rl_out);
                //TransitionDrawable d = (TransitionDrawable) getWindow().getDecorView().getBackground();
                //d.startTransition(2000);
            }
        });
        super.onResume();
    }
}