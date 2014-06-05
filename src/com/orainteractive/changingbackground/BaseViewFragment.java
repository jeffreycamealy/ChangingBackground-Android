package com.orainteractive.changingbackground;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orainteractive.changingbackground.R;

public abstract class BaseViewFragment extends Fragment implements OnClickListener {
	
	private FragmentListener listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (FragmentListener) activity;
		} catch (ClassCastException classCastException) {
			throw new IllegalAccessError("Activity MUST implement FragmentListener");
		}
	}
	
	protected FragmentListener getListener() {
		return listener;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_view, container, false);
		TextView textView = (TextView) view.findViewById(R.id.fragment_base_view_textview);
		textView.setText(getTextViewText());
		Button button = (Button) view.findViewById(R.id.fragment_base_view_button);
		button.setText(getButtonText());
		button.setOnClickListener(this);
		return view;
	}
	
	protected abstract int getTextViewText();
	
	protected abstract int getButtonText();
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_base_view_button:
			onButtonClicked();
			break;
		default:
			break;
		}
	}
	
	protected abstract void onButtonClicked();

}
