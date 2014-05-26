package com.orainteractive.changingbackground;

import android.graphics.drawable.TransitionDrawable;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Fragment forward = ButtonFragment.newInstance(getString(R.string.forward),
				getString(R.string.blue_fragment), true);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.setCustomAnimations(0,0);
		transaction.add(R.id.fragment_frame, forward, getString(R.string.forward));
		transaction.setTransition(1);
		transaction.commit();
	}

	public void buttonClick(View v) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment;

		boolean firstFragment = ((Button) v).getText().equals(getString(R.string.forward));
		if (firstFragment) {
			fragment = fm.findFragmentByTag(getString(R.string.backwards));
			if (fragment == null)
				fragment = ButtonFragment.newInstance(getString(R.string.backwards),
						getString(R.string.green_fragment), false);
			transaction.replace(R.id.fragment_frame, fragment);
			transaction.addToBackStack(getString(R.string.backwards));
//			transaction.setCustomAnimations(R.anim.slide_out_left,
//					R.anim.slide_in_right);
			transaction.commit();
			
		}
		else {
//			fragment = fm.findFragmentByTag(getString(R.string.forward));
//			if (fragment == null)
//				fragment = ButtonFragment.newInstance(getString(R.string.forward),
//						getString(R.string.blue_fragment), false);
			fm.popBackStack();
			// transaction.setCustomAnimations(R.anim.slide_in_left,
			// R.anim.slide_out_right);
		}
		
		//transaction.commit();

	}
}
