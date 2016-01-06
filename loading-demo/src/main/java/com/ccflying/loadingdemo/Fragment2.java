package com.ccflying.loadingdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ccflying.easyloading.FragmentLoading;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment2 extends Fragment {
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingView();
        final View view = getView();
        view.post(new Runnable() {
            @Override
            public void run() {
                Log.e("Fragment2", view.getHeight() + "-" + view.getWidth());
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                Log.e("Fragment2", location[0] + "-" + location[1]);
                view.getLocationInWindow(location);
                Log.e("Fragment2", location[0] + "-" + location[1]);
            }
        });
    }

    private void showLoadingView() {
        View view = FragmentLoading.showLoading(this, R.layout.fragment_simple_loading);
        view.setBackgroundResource(R.drawable.loading_9);
        FragmentLoading.setGravityAndMargins(this, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50, 0, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Loading Failure", Toast.LENGTH_SHORT).show();
                showEmptyRetry();
            }
        }, 2000);
    }

    private void showEmptyRetry() {
        View view = FragmentLoading.showEmpty(this, R.layout.fragment_empty_retry);
        view.findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
            }
        });
    }
}
