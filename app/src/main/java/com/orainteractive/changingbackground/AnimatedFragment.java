package com.orainteractive.changingbackground;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Class to override animation methods so I can be notified when a FragmentTransaction
 * animation is completed.
 * Created by raf on 6/11/14.
 */
public class AnimatedFragment extends Fragment {

    private Animation.AnimationListener animListener;

    public void setAnimListener(Animation.AnimationListener listener){
        this.animListener = listener;
    }

    /**
     * Simple tracing
     * @param txt
     */
    private void line(String txt) {
        Log.i(AnimatedFragment.class.getName(), txt);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        line("--onCreateAnimation--");
        Animation anim = super.onCreateAnimation(transit, enter, nextAnim);

        if(anim != null){
            line(anim.toString());
        }

        if(animListener != null){
            line(animListener.toString());
        }

        if(animListener != null && anim != null){
            anim.setAnimationListener(animListener);
        }

        return anim;
    }
}
