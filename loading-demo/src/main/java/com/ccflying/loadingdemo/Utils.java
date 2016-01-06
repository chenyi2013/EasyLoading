package com.ccflying.loadingdemo;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Created by ccfyyn on 16/1/6.
 */
public class Utils {

    public static FrameLayout getFrameLayout(View view) {
        ViewParent vp = view.getParent();
        while (vp != null) {
            ViewGroup vg = (ViewGroup) vp;
            if (vg instanceof FrameLayout) {
                return (FrameLayout) vg;
            }
            vp = vg.getParent();
        }
        return null;
    }

}
