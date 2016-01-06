package com.ccflying.easyloading;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by youku on 15/9/18.
 */
public class FragmentLoading {
    private static Map<String, WeakReference<View>> map;
    private static View.OnClickListener listener;

    static {
        map = new HashMap<>();
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };
    }

    /**
     * show loading view
     *
     * @param fragment
     * @param layoutId
     * @return
     */
    public static View showLoading(Fragment fragment, int layoutId) {
        View view = LayoutInflater.from(fragment.getActivity()).inflate(layoutId, null);
        showLoading(fragment, view, false);
        return view;
    }

    /**
     * show loading view
     *
     * @param fragment
     * @param layoutId
     * @return
     */
    public static View showLoading(Fragment fragment, int layoutId, boolean dimBackground) {
        View view = LayoutInflater.from(fragment.getActivity()).inflate(layoutId, null);
        showLoading(fragment, view, dimBackground);
        return view;
    }

    /**
     * show loading view
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showLoading(Fragment fragment, View view) {
        return showLoading(fragment, view, false);
    }

    /**
     * show loading view
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showLoading(Fragment fragment, View view, boolean dimBackground) {
        View layout = packingView(fragment, view, dimBackground, true);
        return showView(fragment, layout, -1, -1, Gravity.CENTER);
    }

    /**
     * packing a view to FrameLayout
     *
     * @param fragment
     * @param view
     * @param dimBackground
     * @return
     */
    private static View packingView(Fragment fragment, View view, boolean dimBackground, boolean interceptTouch) {
        FrameLayout layout = new FrameLayout(fragment.getActivity());
        // view's LayoutParams
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
     * show empty view
     *
     * @param fragment
     * @param layoutId
     * @return
     */
    public static View showEmpty(Fragment fragment, int layoutId) {
        View view = LayoutInflater.from(fragment.getActivity()).inflate(layoutId, null);
        showEmpty(fragment, view);
        return view;
    }

    /**
     * show empty view
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showEmpty(Fragment fragment, View view) {
        return showView(fragment, packingView(fragment, view, false, false), -1, -1, Gravity.CENTER);
    }

    /**
     * Add View to Fragment
     *
     * @param fragment
     * @param view
     * @param width
     * @param height
     * @param gravity
     * @return
     */
    public static boolean showView(Fragment fragment, View view, int width, int height, int gravity) {
        dismiss(fragment);
        //
        View parent = fragment.getView();
        if (parent == null) {
            return false;
        }
        // FrameLayout
        if (parent instanceof FrameLayout) {
            FrameLayout layout = (FrameLayout) parent;
            FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(width, height);
            lps.gravity = gravity;
            layout.addView(view, lps);
            // clear reference
            clearEmptyReference();
            // save reference
            WeakReference<View> weakView = new WeakReference<View>(view);
            map.put(fragment.getClass().getSimpleName(), weakView);
            return true;
        }
        throw new IllegalStateException("Fragment's rootView(Fragment#getView()) must be FrameLayout " +
                "or FrameLayout's subclass");
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
     * remove addedView from fragment
     *
     * @param fragment
     */
    public static void dismiss(Fragment fragment) {
        String name = fragment.getClass().getSimpleName();
        WeakReference<View> weakReference = map.remove(name);
        //
        if (null == weakReference) return;
        View view = weakReference.get();
        //
        if (null == view) return;
        // view未被回收
        try {
            ViewGroup vg = (ViewGroup) view.getParent();
            vg.removeView(view);
        } catch (Exception e) {
        }
    }

    /**
     * set gravity and margin
     *
     * @param fragment
     * @param gravity
     * @param leftMargin
     * @param topMargin
     * @param rightMargin
     * @param bottomMargin
     */
    public static void setGravityAndMargins(Fragment fragment, int gravity, int leftMargin,
                                            int topMargin, int rightMargin, int bottomMargin) {
        WeakReference<View> weakReference = map.get(fragment.getClass().getSimpleName());
        if (null != weakReference) {
            View view = weakReference.get();
            if (null != view) {
                view = ((FrameLayout) view).getChildAt(0);
                ViewGroup.LayoutParams tmpLps = view.getLayoutParams();
                if (tmpLps instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams lps = (FrameLayout.LayoutParams) tmpLps;
                    lps.gravity = gravity;
                    lps.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
                    view.setLayoutParams(lps);
                }
            }
        }
    }
}
