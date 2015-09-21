package com.ccflying.loadingdemo;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ccflying.easyloading.ActivityTool;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler = new Handler();
    private ViewPager viewPager;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.showLoading).setOnClickListener(this);
        findViewById(R.id.showLoadingMulti).setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TestAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showLoading) {
            ActivityTool.showLoading(this, R.layout.activity_simple_loading);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityTool.dismiss(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Loading success", Toast.LENGTH_SHORT).show();
                }
            }, 1500);
        } else if (v.getId() == R.id.showLoadingMulti) {
            ActivityTool.showLoading(this, R.layout.activity_multi_loading);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityTool.dismiss(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Loading success", Toast.LENGTH_SHORT).show();
                }
            }, 1500);
        }
    }

    private class TestAdapter extends FragmentPagerAdapter {
        private Fragment1 fragment1;
        private Fragment2 fragment2;

        public TestAdapter(FragmentManager fm) {
            super(fm);
            fragment1 = new Fragment1();
            fragment2 = new Fragment2();
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? fragment1 : fragment2;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
