package com.orainteractive.changingbackground;

import com.orainteractive.changingbackground.R;

public class SecondViewFragment extends BaseViewFragment {
	
	@Override
	protected int getTextViewText() {
		return R.string.fragment_second_view_textview_text;
	}

	@Override
	protected int getButtonText() {
		return R.string.fragment_second_view_button_text;
	}

	@Override
	protected void onButtonClicked() {
		getActivity().onBackPressed();
	}

}
