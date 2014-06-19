package com.orainteractive.changingbackground.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.orainteractive.changingbackground.MainActivity;
import com.orainteractive.changingbackground.R;

public class HomeFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        Button goToSecondFragmentBtn = (Button) rootView.findViewById(R.id.home_button);
        goToSecondFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showSecondFragment();
            }
        });
        return rootView;
    }
}
