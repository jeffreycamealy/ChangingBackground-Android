package com.orainteractive.changingbackground;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

/**
 * The main Activity of the app
 */
public class MainActivity extends FragmentActivity {
	boolean forwardFragActive = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment = null;
		if (savedInstanceState != null) {
			
			// Check for the last displayed fragment if it exists
			forwardFragActive = savedInstanceState.getBoolean("forwardFragment", true);
			fragment = fm.findFragmentByTag(forwardFragActive ? ButtonFragment.forward
					: ButtonFragment.backward);
		}

		if (fragment == null)
			fragment = ButtonFragment.newInstance(forwardFragActive);

		transaction.replace(R.id.fragment_frame, fragment,
				forwardFragActive ? ButtonFragment.forward : ButtonFragment.backward);
		transaction.commit();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("forwardFragment", forwardFragActive);
		super.onSaveInstanceState(outState);
	}

	public void buttonClick(View v) {

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();

		// Set forwardFragActive to the desired fragment.
		forwardFragActive = !((Button) v).getText().equals(ButtonFragment.forward);
		
		// Check if the fragment exists, otherwise create a new instance.
		Fragment fragment = fm.findFragmentByTag(forwardFragActive ? ButtonFragment.forward
				: ButtonFragment.backward);
		if (fragment == null)
			fragment = ButtonFragment.newInstance(forwardFragActive);
		
		transaction.replace(R.id.fragment_frame, fragment);
		transaction.commit();
	}
}
