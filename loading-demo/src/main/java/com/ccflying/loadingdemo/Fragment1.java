package com.ccflying.loadingdemo;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.ccflying.easyloading.FragmentV4Tool;
import com.ccflying.easyloading.LoadingSupportFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment1 extends LoadingSupportFragment implements View.OnClickListener {
    Handler handler = new Handler();

    @Override
    protected int contentViewLayoutId() {
        return R.layout.fragment_extends_loadingfragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.showLoading).setOnClickListener(this);
        view.findViewById(R.id.showLoadingWithDim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showLoadingWithDim) {
            FragmentV4Tool.showLoading(this, R.layout.fragment_simple_loading, true);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentV4Tool.dismiss(Fragment1.this);
                    Toast.makeText(getActivity(), "Loading over", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        } else {
            FragmentV4Tool.showLoading(this, R.layout.fragment_simple_loading);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentV4Tool.dismiss(Fragment1.this);
                    Toast.makeText(getActivity(), "Loading over", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        }
    }
}
