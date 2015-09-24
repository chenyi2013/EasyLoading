package com.ccflying.loadingdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ccflying.easyloading.FragmentV4Tool;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment2 extends Fragment {
    private Handler handler = new Handler() {
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_original, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingView();
    }

    private void showLoadingView() {
        View view = FragmentV4Tool.showLoading(this, R.layout.fragment_simple_loading);
        view.setBackgroundResource(R.drawable.loading_9);
        FragmentV4Tool.setGravityAndMargins(this, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50, 0, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Loading Failure", Toast.LENGTH_SHORT).show();
                showEmptyRetry();
            }
        }, 2000);
    }

    private void showEmptyRetry() {
        View view = FragmentV4Tool.showEmpty(this, R.layout.fragment_empty_retry);
        view.findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
            }
        });
    }
}
