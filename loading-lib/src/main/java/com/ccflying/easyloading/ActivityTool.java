package com.ccflying.easyloading;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by youku on 15/9/14.
 */
public class ActivityTool {
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
     * add loading view to activity
     *
     * @param activity
     * @param layoutId
     * @return
     */
    public static View showLoading(Activity activity, int layoutId) {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        showLoading(activity, view, true);
        return view;
    }

    /**
     * add loading view to activity
     *
     * @param activity
     * @param layoutId
     * @return
     */
    public static View showLoading(Activity activity, int layoutId, boolean dimBackground) {
        View view = LayoutInflater.from(activity).inflate(layoutId, null);
        showLoading(activity, view, dimBackground);
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
        return showLoading(activity, view, true);
    }

    /**
     * add loading view to activity
     *
     * @param activity
     * @param view
     * @return
     */
    public static boolean showLoading(Activity activity, View view, boolean dimBackground) {
        FrameLayout layout = new FrameLayout(activity);
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(-2, -2);
        if (dimBackground) {
            layout.setBackgroundColor(0x88000000);
        }
        lps.gravity = Gravity.CENTER;
        layout.addView(view, lps);
        layout.setOnClickListener(listener);
        return showView(activity, layout, -1, -1, Gravity.CENTER);
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
        return showView(activity, view, -2, -2, Gravity.CENTER);
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
        dismiss(activity);
        //
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(width, height);
        lps.gravity = gravity;
        activity.addContentView(view, lps);
        map.put(activity.getClass().getSimpleName(), view);
        return true;
    }

    /**
     * remove view from activity
     */
    public static boolean dismiss(Activity activity) {
        View view = map.remove(activity.getClass().getSimpleName());
        if (null != view) {
            View parent = (View) view.getParent();
            if (null != parent)
                try {
                    ViewGroup parentGroup = (ViewGroup) parent;
                    parentGroup.removeView(view);
                    return true;
                } catch (Exception e) {
                }
        }
        return false;
    }
}
