package com.orainteractive.changingbackground;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment that shows the "Go back" button.
 * 
 * @author Federico Baseggio
 */
public class Fragment2 extends BaseFragment {
	
	@Override
	protected int getFragmentNumber() {
		return 2;
	}

	public static Fragment2 newInstance() {
		return new Fragment2();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment2, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button backButton = (Button) getView().findViewById(R.id.backButton);
		
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment2.this.getFragmentManager().popBackStack();
			}
		});
	}
}