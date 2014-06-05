package com.orainteractive.changingbackground;

import com.orainteractive.changingbackground.R;

public class HomeViewFragment extends BaseViewFragment {

	@Override
	protected int getTextViewText() {
		return R.string.fragment_home_view_textview_text;
	}

	@Override
	protected int getButtonText() {
		return R.string.fragment_home_view_button_text;
	}

	@Override
	protected void onButtonClicked() {
		getListener().replaceFragment(new SecondViewFragment(), true);
	}

}
