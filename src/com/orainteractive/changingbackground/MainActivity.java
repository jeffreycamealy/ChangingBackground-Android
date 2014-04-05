package com.orainteractive.changingbackground;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * The main Activity of the app.
 * Default manual swiping (with gesture) between pages of {@link android.support.v4.view.ViewPager} is disabled.
 * Instead of {@link android.support.v4.view.ViewPager} Activity view is represented by {@link NoManualSwipeViewPager}.
 * 
 * @extends {@link android.support.v4.app.FragmentActivity}
 * @implements {@link ISwipable}
 * 
 * @author Artem Vynohradov
 */
public class MainActivity extends FragmentActivity implements ISwipable {
	
	/**
	 * The tag for logging purposes
	 */
	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	/**
	 * The custom animation duration of swiping between fragments
	 */
	private static final int ANIMATION_DURATION = 500;
	
	/**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
	private ViewPager mViewPager;
	
	/**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every loaded fragment in memory. If this becomes too
     * memory intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
	private SwipingPagerAdapter mSwipingPagerAdapter;
	
	/**
	 * The index of the current fragment that the user interacts with.
	 */
	private int mCurrentPosition;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Initializing SwipingPagerAdapter
        mSwipingPagerAdapter = new SwipingPagerAdapter(getSupportFragmentManager());
        
        // Initializing ViewPager
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSwipingPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPagerListener());        
        
        // Initializing interpolator for swiping animation
        DecelerateInterpolator interpolator = new DecelerateInterpolator();
        
        // Injecting custom Scroller into ViewPager 
        try {
        	Field scrollerField;
        	scrollerField = ViewPager.class.getDeclaredField("mScroller");
        	scrollerField.setAccessible(true);
        	CustomSpeedScroller scroller = new CustomSpeedScroller(getApplicationContext(), interpolator);
        	scrollerField.set(mViewPager, scroller);
        } catch (NoSuchFieldException e) {
        	e.printStackTrace();
        } catch (IllegalAccessException e) {
        	e.printStackTrace();
        } catch (IllegalArgumentException e) {
        	e.printStackTrace();
        }
    }    

	@Override
	public void swipeForward() {
		// Check if forward shifting is available
		if (mCurrentPosition < mSwipingPagerAdapter.getCount()-1) {
			
			// Shifting to the next fragment with smooth scroll
			mViewPager.setCurrentItem(mCurrentPosition+1, true);
		}
	}
	
	@Override
	public void swipeBackward() {
		// Check if backward shifting is available
		if (mCurrentPosition > 0) {
			
			// Shifting to the previous fragment with smooth scroll
			mViewPager.setCurrentItem(mCurrentPosition-1, true);
		}
	}
	
    /**
     * The custom implementation of {@link android.support.v4.app.FragmentPagerAdapter}.
     * Returns a fragment corresponding to one of the sections/tabs/pages.
     * To change the number of available fragments adjust {@link SwipingPagerAdapter#getCount()} method.
     * 
     * @extends {@link android.support.v4.app.FragmentPagerAdapter}
     * 
     * @author Artem Vynohradov
     */
	private class SwipingPagerAdapter extends FragmentPagerAdapter {		

		public SwipingPagerAdapter(FragmentManager fm) {
			super(fm);			
		}

		@Override
		public Fragment getItem(int position) {
			// Contructing and returning new instance of SwipingFragment
			return SwipingFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			// Only two fragment are available
			return 2;
		}
    	
    }
	
	/**
	 * The custom implementation of {@link android.support.v4.view.ViewPager.OnPageChangeListener}.
	 * Provides callback interface for responding to changing state of the selected page.
	 * To change transition animation speed adjust {@link ViewPagerListener#onPageSelected(int)} method.
	 * 
	 * @implements {@link android.support.v4.view.ViewPager.OnPageChangeListener}
	 * 
	 * @author Artem Vynohradov
	 */
	private class ViewPagerListener implements OnPageChangeListener {
		
		/**
		 * The {@link TransitionDrawable} that provides transition between specified drawable resources.
		 */
		private TransitionDrawable mTransitionDrawable;
		
		public ViewPagerListener() {
			//Initializing TransitionDrawable from ViewPager background drawable
			mTransitionDrawable = (TransitionDrawable) mViewPager.getBackground();
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			// TODO Auto-generated method stub			
		}

		@Override
		public void onPageSelected(int position) {
			// Setting current fragment position
			mCurrentPosition = position;			
			
			switch (position) {
			
			// Shifting from 1-position fragment to 0-position one (backward)
			case 0:	
				
				// Switching from second drawable to first one
				mTransitionDrawable.reverseTransition(ANIMATION_DURATION*2);
				break;
				
			// Shifting from 0-position fragment to 1-position one (forward)
			case 1:
				
				// Switching from first drawable to second one
				mTransitionDrawable.startTransition(ANIMATION_DURATION*2);
				break;
			default:
				break;
			}			
		}
		
	}
	
	/**
	 * The custom implementation of {@link Scroller}.
	 * Provides 
	 * To change swiping animation speed adjust {@link CustomSpeedScroller#setCustomDuration(int)} method.
	 * 
	 * @extends {@link Scroller}
	 * 
	 * @author Artem Vynohradov
	 */
	private class CustomSpeedScroller extends Scroller {
		
		/**
		 * The custom animation duration of swiping between fragments.
		 */
		private int mDuration = ANIMATION_DURATION;

        public CustomSpeedScroller(Context context) {
            super(context);
        }

        public CustomSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public CustomSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }
        
        /**
         * Sets custom animation duration of swiping between fragments.
         * @param customDuration - Custom animation duration (in ms)
         */
        public void setCustomDuration(int customDuration) {        	
        	// Duration cannot be negative
            if (customDuration >= 0) {
                mDuration = customDuration;
            }
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
        	// Ignoring default Scroller duration value
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        	// Ignoring provided duration value
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
        
	}
	
}
