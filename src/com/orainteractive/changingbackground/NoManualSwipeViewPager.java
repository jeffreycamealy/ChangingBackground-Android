package com.orainteractive.changingbackground;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * The custom implementation of {@link android.support.v4.view.ViewPager}.
 * Default manual swiping (with gesture) between pages is disabled.
 * To adjust manual swiping functionality see {@link NoManualSwipeViewPager#onTouchEvent(MotionEvent)} and 
 * {@link NoManualSwipeViewPager#onInterceptTouchEvent(MotionEvent)} methods.
 * 
 * @extends {@link android.support.v4.view.ViewPager}
 * 
 * @author Artem Vynohradov
 */
public class NoManualSwipeViewPager extends ViewPager {
	
	public NoManualSwipeViewPager(Context context) {
		super(context);
	}

	public NoManualSwipeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Disabling manual swiping
		return false;
//		return super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		// Disabling manual swiping
		return false;
//		return super.onInterceptTouchEvent(event);
	}
	
	

}
