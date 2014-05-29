package com.orainteractive.changingbackground;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * Activity that holds all fragments.
 * 
 * @author Federico Baseggio
 */
public class MainActivity extends FragmentActivity implements
		BaseFragment.FragmentListener {

	private ImageSwitcher bgSwitcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		
		bgSwitcher = (ImageSwitcher) findViewById(R.id.backgroundSwitcher);
		bgSwitcher.setFactory(new ViewFactory() {

			public View makeView() {
				ImageView imageView = new ImageView(getApplicationContext());
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				return imageView;
			}
		});

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragmentContainer, Fragment1.newInstance())
					.commit();
		}
	}

	@Override
	public void onFragmentVisible(int fragmentNum) {
		bgSwitcher.setImageResource(fragmentNum == 1 ? R.drawable.green : R.drawable.blue);
	}

	@Override
	public void onNextSelected() {
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.anim.slide_in, R.anim.slide_out,
						android.R.anim.slide_in_left,
						android.R.anim.slide_out_right)
				.replace(R.id.fragmentContainer, Fragment2.newInstance())
				.addToBackStack(null).commit();
	}
}
