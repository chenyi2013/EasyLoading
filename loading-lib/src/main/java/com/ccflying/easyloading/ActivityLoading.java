package com.ccflying.easyloading;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by youku on 15/9/14.
 */
public class ActivityLoading {
    private static Map<String, WeakReference<View>> map;
    private static View.OnClickListener listener;
    private static int viewTag = 2131492143;

    static {
        map = new HashMap<>();
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };
    }

    /**
     * add loading view to activity
     *
     * @param activity
     * @param layoutId
     * @return
     */
    public static View showLoading(Activity activity, int layoutId) {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        showLoading(activity, view, true, true, null);
        return view;
    }

    /**
     * add loading view to activity
     *
     * @param activity
     * @param layoutId
     * @return
     */
    public static View showLoading(Activity activity, int layoutId, boolean dimBackground, boolean showAnimation, Animation anim) {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        showLoading(activity, view, dimBackground, showAnimation, anim);
        return view;
    }

    /**
     * add loading view to activity
     *
     * @param activity
     * @param view
     * @return
     */
    public static boolean showLoading(Activity activity, View view) {
        return showLoading(activity, view, true, true, null);
    }

    /**
     * add loading view to activity
     *
     * @param activity
     * @param view
     * @return
     */
    public static boolean showLoading(Activity activity, View view, boolean dimBackground, boolean showAnimation, Animation anim) {
        View layout = packingView(activity, view, dimBackground, true);
        if (showAnimation) {
            if (null == anim) {
                anim = LoadingAnimations.getShowingAnimation();
            }
            if (dimBackground) {
                layout.startAnimation(anim);
                layout.setTag(viewTag, 0);
            } else {
                view.startAnimation(anim);
                layout.setTag(viewTag, 1);
            }
        }
        return showView(activity, layout, -1, -1, Gravity.CENTER);
    }

    /**
     * packing a view to framelayout
     *
     * @param activity
     * @param view
     * @param dimBackground
     * @param interceptTouch
     * @return
     */
    private static View packingView(Activity activity, View view, boolean dimBackground, boolean interceptTouch) {
        FrameLayout layout = new FrameLayout(activity);
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(-2, -2);
        lps.gravity = Gravity.CENTER;
        layout.addView(view, lps);
        if (dimBackground) {
            layout.setBackgroundColor(0x88000000);
        }
        if (interceptTouch) {
            layout.setOnClickListener(listener);
        }
        return layout;
    }

    /**
     * add emptyView to activity
     *
     * @param activity
     * @param layoutId
     * @return
     */
    public static View showEmpty(Activity activity, int layoutId) {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        showEmpty(activity, view);
        return view;
    }

    /**
     * add emptyView to activity
     *
     * @param activity
     * @param view
     * @return
     */
    public static boolean showEmpty(Activity activity, View view) {
        return showView(activity, packingView(activity, view, false, false), -1, -1, Gravity.CENTER);
    }

    /**
     * add view to activity
     *
     * @param activity
     * @param view
     * @param width
     * @param height
     * @param gravity
     * @return
     */
    public static boolean showView(Activity activity, View view, int width, int height, int gravity) {
        dismiss(activity, true, null);
        //
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(width, height);
        lps.gravity = gravity;
        activity.addContentView(view, lps);
        // clear reference
        clearEmptyReference();
        // save reference
        WeakReference<View> weakView = new WeakReference<View>(view);
        map.put(activity.getClass().getSimpleName(), weakView);
        //
        return true;
    }

    /**
     * clear reference
     */
    private static void clearEmptyReference() {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            WeakReference<View> weakReference = map.get(key);
            if (null != weakReference && null == weakReference.get()) {
                map.remove(key);
            }
        }
    }

    /**
     * remove view from activity
     *
     * @param activity
     * @return
     */
    public static boolean dismiss(Activity activity) {
        return dismiss(activity, true, null);
    }

    /**
     * remove view from activity
     *
     * @param activity
     * @param showAnim
     * @param anim
     * @return
     */
    public static boolean dismiss(Activity activity, boolean showAnim, Animation anim) {
        WeakReference<View> weakView = map.remove(activity.getClass().getSimpleName());
        if (null == weakView) return false;
        //
        final View view = weakView.get();
        if (null == view) return true;
        // view未被回收
        ViewParent parent = view.getParent();
        if (null != parent)
            try {
                final ViewGroup parentGroup = (ViewGroup) parent;
                view.setOnClickListener(null);
                Object tag = view.getTag(viewTag);
                if (null == tag || !showAnim) {
                    parentGroup.removeView(view);
                    return true;
                }
                if (null == anim) anim = LoadingAnimations.getDismissingAnimation();
                if (tag == 0) {
                    view.startAnimation(anim);
                } else if (view instanceof FrameLayout && ((FrameLayout) view).getChildCount() > 0) {
                    ((FrameLayout) view).getChildAt(0).startAnimation(anim);
                }
                parentGroup.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parentGroup.removeView(view);
                    }
                }, Math.abs(anim.getDuration() - 20));
            } catch (Exception e) {
            }
        return true;
    }

    /**
     * set gravity and margin
     *
     * @param activity
     * @param gravity
     * @param leftMargin
     * @param topMargin
     * @param rightMargin
     * @param bottomMargin
     */
    public static void setGravityAndMargins(Activity activity, int gravity, int leftMargin,
                                            int topMargin, int rightMargin, int bottomMargin) {
        WeakReference<View> weakReference = map.get(activity.getClass().getSimpleName());
        if (null != weakReference) {
            View view = weakReference.get();
            if (null != view) {
                view = ((FrameLayout) view).getChildAt(0);
                FrameLayout.LayoutParams lps = (FrameLayout.LayoutParams) view.getLayoutParams();
                lps.gravity = gravity;
                lps.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
                view.setLayoutParams(lps);
            }
        }
    }
}
