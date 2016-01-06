package com.ccflying.loadingdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ccflying.easyloading.ActivityLoading;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.showLoading).setOnClickListener(this);
        findViewById(R.id.showLoadingMulti).setOnClickListener(this);
        findViewById(R.id.showLoading4ViewPager).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showLoading) {
            ActivityLoading.showLoading(this, R.layout.activity_loading_simple);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityLoading.dismiss(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Loading success", Toast.LENGTH_SHORT).show();
                }
            }, 1500);
        } else if (v.getId() == R.id.showLoadingMulti) {
            ActivityLoading.showLoading(this, R.layout.activity_loading_withtext);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityLoading.dismiss(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Loading success", Toast.LENGTH_SHORT).show();
                }
            }, 1500);
        } else if (v.getId() == R.id.showLoading4ViewPager) {
            startActivity(new Intent(this, ViewPagerActivity.class));
        }
    }


}
