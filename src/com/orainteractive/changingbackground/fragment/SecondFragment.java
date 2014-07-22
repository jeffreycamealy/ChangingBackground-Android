package com.orainteractive.changingbackground.fragment;

import com.orainteractive.changingbackground.R;

/**
 * A Fragment whose button specifies moving back.
 */
public class SecondFragment extends BaseFragment {

    @Override
    protected String getLabelText() {
        return getString(R.string.second_fragment_label);
    }

    @Override
    protected String getButtonText() {
        return getString(R.string.second_fragment_button);
    }
}
