package com.orainteractive.changingbackground.fragment;

import com.orainteractive.changingbackground.R;

/**
 * A Fragment whose button specifies moving forward.
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    protected String getLabelText() {
        return getString(R.string.home_fragment_label);
    }

    @Override
    protected String getButtonText() {
        return getString(R.string.home_fragment_button);
    }
}
