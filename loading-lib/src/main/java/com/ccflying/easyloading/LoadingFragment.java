package com.ccflying.easyloading;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by youku on 15/9/14.
 */
public abstract class LoadingFragment extends Fragment {
    private FrameLayout mContentView;

    protected abstract int contentViewLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = new FrameLayout(getActivity());
        inflater.inflate(contentViewLayoutId(), mContentView, true);
        return mContentView;
    }
}
