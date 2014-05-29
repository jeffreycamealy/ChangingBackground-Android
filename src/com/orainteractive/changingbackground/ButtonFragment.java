package com.orainteractive.changingbackground;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ButtonFragment extends Fragment {

	public final static String forward = "Forward";
	public final static String backward = "Backward";
	private final static String blue = "Blue Fragment";
	private final static String green = "Green Fragment";

	public boolean shouldAnimate = false;

	// Use the newInstance/arguments bundle so our fragments can be recreated
	// automatically with the required arguments.

	public static ButtonFragment newInstance(boolean isForward) {
		ButtonFragment fragment = new ButtonFragment();
		Bundle args = new Bundle();
		args.putString("buttonText", isForward ? forward : backward);
		args.putString("fragmentText", isForward ? blue : green);
		args.putBoolean("forward", isForward);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment, container, false);
		((Button) v.findViewById(R.id.btn_forward)).setText(getArguments().getString("buttonText"));
		((TextView) v.findViewById(R.id.fragment_text)).setText(getArguments().getString(
				"fragmentText"));
		return v;
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {

		final boolean forward = getArguments().getBoolean("forward");
		final int animId = forward ? (enter ? R.anim.slide_in_left : R.anim.slide_out_left)
				: (enter ? R.anim.slide_in_right : R.anim.slide_out_right);
		Animation anim = super.onCreateAnimation(transit, enter, nextAnim);

		// When starting or after rotating we want the fragment to appear, not
		// slide in.
		if (shouldAnimate) {
			anim = AnimationUtils.loadAnimation(getActivity(), animId);

			// The color transitions needs to happen AFTER the sliding in/out,
			// we do this with an AnimationListener

			anim.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					// Transition the background
					TransitionDrawable transition = (TransitionDrawable) getActivity()
							.findViewById(R.id.fragment_frame).getBackground();
					if (forward)
						transition.reverseTransition(500);
					else
						transition.startTransition(500);
				}
			});
		}
		else {
			// If we are not animating, then this is a new fragment. If it is
			// the backward/green fragment we need to start with the background
			// transitioned
			TransitionDrawable transition = ((TransitionDrawable) getActivity().findViewById(
					R.id.fragment_frame).getBackground());
			if (!forward)
				transition.startTransition(0);
		}
		shouldAnimate = true;
		return anim;
	}
}
