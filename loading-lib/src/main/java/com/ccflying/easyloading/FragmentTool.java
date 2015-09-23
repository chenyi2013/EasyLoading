package com.ccflying.easyloading;

import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by youku on 15/9/18.
 */
public class FragmentTool {
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
     * show loading
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
     * show loading
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
     * show loading
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showLoading(Fragment fragment, View view) {
        return showLoading(fragment, view, false);
    }

    /**
     * show loading
     *
     * @param fragment
     * @param view
     * @return
     */
    public static boolean showLoading(Fragment fragment, View view, boolean dimBackground) {
        FrameLayout layout = new FrameLayout(fragment.getActivity());
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(-2, -2);
        lps.gravity = Gravity.CENTER;
        layout.addView(view, lps);
        if (dimBackground) {
            layout.setBackgroundColor(0x88000000);
        }
        layout.setOnClickListener(listener);
        return showView(fragment, layout, -1, -1, Gravity.CENTER);
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
        //
        View parent = fragment.getView();
        if (parent == null) {
            return false;
        }
        try {
            FrameLayout layout = (FrameLayout) parent;
            FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(width, height);
            lps.gravity = gravity;
            layout.addView(view, lps);
            // clear reference
            clearEmptyReference();
            // save reference
            WeakReference<View> weakView = new WeakReference<View>(view);
            map.put(fragment.getClass().getSimpleName(), weakView);
        } catch (Exception e) {
            throw new IllegalStateException("Fragment's view must be FrameLayout or FrameLayout's subclass");
        }
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
}