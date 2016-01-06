package com.ccflying.loadingdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ccfyyn on 16/1/6.
 */
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TestAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TestAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
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
