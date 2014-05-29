package com.orainteractive.changingbackground;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Fragment that enables interaction between fragment instances and the
 * activity.
 * 
 * @author Federico Baseggio
 */
public abstract class BaseFragment extends Fragment {

	private FragmentListener listener;

	public static interface FragmentListener {
		
		/**
		 * Called when a fragment becomes visible
		 * 
		 * @param fragmentNum Number identifying the fragment
		 */
		public void onFragmentVisible(int fragmentNum);

		/**
		 * Called when the "Go forward" button is clicked
		 */
		public void onNextSelected();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (FragmentListener) activity;
	}
	
	@Override
	public void onDetach() {
		listener = null;
		super.onDetach();
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		if (listener != null) {
			listener.onFragmentVisible(getFragmentNumber());
		}
	}
	
	/**
	 * Signals the activity that the "Go forward" button was clicked
	 */
	protected void nextSelected() {
		if (listener != null) {
			listener.onNextSelected();
		}
	}
	
	/**
	 *  Gets the number that identifies this fragment
	 *  
	 * @return The number that identifies this fragment
	 */
	abstract protected int getFragmentNumber();
}
