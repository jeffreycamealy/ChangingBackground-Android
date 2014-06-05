package com.orainteractive.changingbackground;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.orainteractive.changingbackground.R;

/**
 * The main Activity of the app
 */
public class MainActivity extends FragmentActivity implements FragmentListener {
	
	private static final int ANIM_TIME = 3000;
	
	private TransitionDrawable transitionDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_main_linear_layout);
		transitionDrawable = (TransitionDrawable) linearLayout.getBackground();
		if (savedInstanceState == null) {
			replaceFragment(new HomeViewFragment(), false);
		}
	}

	@Override
	public void replaceFragment(Fragment fragment, boolean useAnimation) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		if (useAnimation) {
			 fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
			 transitionDrawable.startTransition(ANIM_TIME);
		}
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.replace(R.id.main_fragment_content, fragment);
		fragmentTransaction.commitAllowingStateLoss();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		transitionDrawable.reverseTransition(ANIM_TIME);
	}
}
