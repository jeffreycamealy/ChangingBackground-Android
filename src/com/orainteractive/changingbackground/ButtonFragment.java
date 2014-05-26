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

	public static ButtonFragment newInstance(String buttonText, String fragmentText, boolean forward) {
		ButtonFragment fragment = new ButtonFragment();
		Bundle args = new Bundle();
		args.putString("buttonText", buttonText);
		args.putString("fragmentText", fragmentText);
		args.putBoolean("forward", forward);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment, container, false);
		((Button) v.findViewById(R.id.btn_forward)).setText(getArguments().getString("buttonText"));
		((TextView) v.findViewById(R.id.fragment_text)).setText(getArguments().getString("fragmentText"));
		return v;
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		final boolean forward = getArguments().getBoolean("forward");
		final int animId = forward ? (enter ? R.anim.slide_in_left : R.anim.slide_out_left)
				: (enter ? R.anim.slide_in_right : R.anim.slide_out_right);
		Animation anim = null;
		if (transit == 0) {
			anim = AnimationUtils.loadAnimation(getActivity(), animId);
			anim.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					TransitionDrawable transition = (TransitionDrawable) getActivity()
							.findViewById(R.id.fragment_frame).getBackground();
					if (forward)
						transition.reverseTransition(500);
					else
						transition.startTransition(500);
				}
			});
		}
		return anim;
	}
}
