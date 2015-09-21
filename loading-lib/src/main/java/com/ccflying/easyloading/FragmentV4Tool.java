package com.ccflying.easyloading;

import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youku on 15/9/18.
 */
public class FragmentV4Tool {
    private static Map<String, View> map;
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
     * show loading
     *
     * @param fragment
     * @param layoutId
     * @return
     */
    public static View showLoading(Fragment fragment, int layoutId) {
        View view = LayoutInflater.from(fragment.getActivity()).inflate(layoutId, null);
        showLoading(fragment, view);
        return view;
    }

    /**
     * show loading
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showLoading(Fragment fragment, View view) {
        boolean viewIsGroup = false;
        try {
            ViewGroup vg = (ViewGroup) view;
            viewIsGroup = true;
        } catch (Exception e) {
        }
        if (viewIsGroup) {
            view.setOnClickListener(listener);
            return showView(fragment, view, -1, -1, Gravity.CENTER);
        } else {
            FrameLayout layout = new FrameLayout(fragment.getActivity());
            FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(-2, -2);
            lps.gravity = Gravity.CENTER;
            layout.addView(view, lps);
            layout.setOnClickListener(listener);
            return showView(fragment, layout, -1, -1, Gravity.CENTER);
        }
    }

    /**
     * show empty
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
     * show empty
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showEmpty(Fragment fragment, View view) {
        return showView(fragment, view, -2, -2, Gravity.CENTER);
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
        if (map.size() > 2) {
            map.clear();
        }
        View parent = fragment.getView();
        if (parent == null) {
            return false;
        }
        try {
            FrameLayout layout = (FrameLayout) parent;
            FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(width, height);
            lps.gravity = gravity;
            layout.addView(view, lps);
            map.put(fragment.getClass().getSimpleName(), view);
        } catch (Exception e) {
            throw new IllegalStateException("Fragment's view must be FrameLayout or FrameLayout's subclass");
        }
        return true;
    }

    /**
     * remove addedView from fragment
     *
     * @param fragment
     */
    public static void dismiss(Fragment fragment) {
        String name = fragment.getClass().getSimpleName();
        View view = map.remove(name);
        if (null == view) return;
        try {
            ViewGroup vg = (ViewGroup) view.getParent();
            vg.removeView(view);
        } catch (Exception e) {
        }
    }
}
