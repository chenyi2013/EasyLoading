package com.ccflying.easyloading;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by ccfyyn on 16/1/7.
 */
public class LoadingAnimations {

    /**
     * get the default animation when show EmptyView/LoadingView
     *
     * @return
     */
    public static Animation getShowingAnimation() {
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setFillAfter(true);
        animScale.setDuration(200);
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setFillAfter(true);
        animAlpha.setDuration(200);
        AnimationSet as = new AnimationSet(true);
        as.addAnimation(animScale);
        as.addAnimation(animAlpha);
        return as;
    }

    /**
     * get the default animation when dismiss EmptyView/LoadingView
     *
     * @return
     */
    public static Animation getDismissingAnimation() {
        ScaleAnimation animScale = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setFillAfter(true);
        animScale.setDuration(200);
        AlphaAnimation animAlpha = new AlphaAnimation(1, 0);
        animAlpha.setFillAfter(true);
        animAlpha.setDuration(200);
        AnimationSet as = new AnimationSet(true);
        as.addAnimation(animScale);
        as.addAnimation(animAlpha);
        return as;
    }
}
