package com.orainteractive.changingbackground;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * The custom {@link android.support.v4.app.Fragment} that represents pages in {@link MainActivity} view.
 * 
 * @extends {@link android.support.v4.app.Fragment}
 * 
 * @author Artem Vynohradov
 */
public class SwipingFragment extends Fragment {
	
	/**
	 * The tag for logging purposes
	 */
	private static final String LOG_TAG = SwipingFragment.class.getSimpleName();
	
	/**
	 * The key for fragment position in {@link android.support.v4.app.FragmentPagerAdapter}
	 */
	private static final String ARG_FRAGMENT_POSITION = "fragment_position";
	
	/**
	 * Provides new instance of {@link SwipingFragment} class each time this method is being invoked.
	 * Initialization is implemented via {@link android.support.v4.app.Fragment#setArguments(Bundle)} method.
	 * @param fragmentPosition - fragment position
	 * @return Instance of {@link SwipingFragment} class
	 */
	public static SwipingFragment newInstance(int fragmentPosition) {
		SwipingFragment fragment = new SwipingFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_FRAGMENT_POSITION, fragmentPosition);
		fragment.setArguments(args);
		return fragment;
	}
	
	/**
	 * Default constructor is necessary for proper construction
	 */
	public SwipingFragment() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflating root view for this fragment
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		
		// Initializing button that is responsible for swiping fragment
		Button swipeButton = (Button) rootView.findViewById(R.id.bt_swipe);
		
		// Initializing text view 
		TextView swipeTextView = (TextView) rootView.findViewById(R.id.tv_swipe);
		
		// Obtaining fragment position from arguments
		final int fragmentPosition = getArguments().getInt(ARG_FRAGMENT_POSITION);
		
		switch (fragmentPosition) {
		
		// First (home) fragment
		case 0:
			swipeTextView.setText(R.string.home_view);
			swipeButton.setText(R.string.go_forward);
			break;
			
		// Second fragment
		case 1:
			swipeTextView.setText(R.string.second_view);
			swipeButton.setText(R.string.go_back);
			break;
		default:
			break;
		}
		
		// Check if parent activity supports swiping functionality
		if (getActivity() instanceof ISwipable) {
			
			// Obtaining parent activity reference
			final ISwipable parentActivity = (ISwipable) getActivity();
			
			// Providing corresponding behavior when button is clicked
			swipeButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					switch (fragmentPosition) {
					
					// First (home) fragment
					case 0:
						
						// Swiping forward
						parentActivity.swipeForward();
						break;
						
					// Second fragment
					case 1:
						
						// Swiping backward
						parentActivity.swipeBackward();
						break;
					default:
						break;
					}
					
				}
			});
		}	
		
		// Returning constructed view that represents fragment
		return rootView;		
	}

	
	
}
