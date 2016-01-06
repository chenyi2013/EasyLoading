package com.ccflying.loadingdemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ccflying.easyloading.FragmentLoading;
import com.ccflying.easyloading.LoadingSupportFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment1 extends LoadingSupportFragment implements View.OnClickListener {
    private Handler handler = new Handler();

    @Override
    protected int contentViewLayoutId() {
        return R.layout.fragment_1;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.showLoading).setOnClickListener(this);
        view.findViewById(R.id.showLoadingWithDim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showLoadingWithDim) {
            FragmentLoading.showLoading(this, R.layout.fragment_simple_loading, true);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentLoading.dismiss(Fragment1.this);
                    Toast.makeText(getActivity(), "Loading over", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        } else {
            FragmentLoading.showLoading(this, R.layout.fragment_simple_loading);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentLoading.dismiss(Fragment1.this);
                    Toast.makeText(getActivity(), "Loading over", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final View view = getView();
        view.post(new Runnable() {
            @Override
            public void run() {
                Log.e("Fragment1", view.getHeight() + "-" + view.getWidth());
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                Log.e("Fragment1", location[0] + "-" + location[1]);
                view.getLocationInWindow(location);
                Log.e("Fragment1", location[0] + "-" + location[1]);
                // Log.e("tag0", view.getParent() + "");
            }
        });
    }
}
