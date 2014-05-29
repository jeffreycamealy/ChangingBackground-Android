package com.orainteractive.changingbackground;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment that shows the "Go forward" button.
 * 
 * @author Federico Baseggio
 */
public class Fragment1 extends BaseFragment {

	@Override
	protected int getFragmentNumber() {
		return 1;
	}

	public static Fragment1 newInstance() {
		return new Fragment1();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment1, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button backButton = (Button) getView().findViewById(R.id.nextButton);
		
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextSelected();
			}
		});
	}
}