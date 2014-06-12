package com.orainteractive.changingbackground;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * The main Activity of the app
 */
public class SecondFragment extends Fragment {

    // create the layout.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    private Button transBt;

    /**
     * Simple tracing
     * @param txt
     */
    private void line(String txt) {
        Log.i(SecondFragment.class.getName(), txt);
    }

    @Override
    public void onResume() {
        line("--onResume--");
        Button nextBt = (Button) getView().findViewById(R.id.trans_bt);
        //reference to the main activity
        final MainActivity main = (MainActivity) getActivity();
        //this isn't going to be another activity though.
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.goPrev();
            }
        });
        super.onResume();
    }

}