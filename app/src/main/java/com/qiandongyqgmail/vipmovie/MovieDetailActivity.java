package com.qiandongyqgmail.vipmovie;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener {

    private int mPosition = 0;
    private String[] url = null;
    private String[] title = null;
    private ViewPager detail_content_viewPager = null;
    private TabLayout detail_nav_tab = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        mPosition = intent.getIntExtra("title", 0);
        Resources resources = getResources();
        url = resources.getStringArray(R.array.movie_url);
        title = resources.getStringArray(R.array.movie_title);

        detail_content_viewPager = (ViewPager) findViewById(R.id.movie_detail_viewpager);
        setupViewPager(detail_content_viewPager);
        detail_nav_tab = (TabLayout) findViewById(R.id.movie_nav_tabs);
        detail_nav_tab.setupWithViewPager(detail_content_viewPager);
        detail_nav_tab.addOnTabSelectedListener(this);
        setTabIcons(true);

    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        MovieDetailFragment md = new MovieDetailFragment();
        md.setParameter(url[mPosition]);
        adapter.addFragment(md, "Detail");
        MovieReviewFragment mr = new MovieReviewFragment();
        mr.setParameter(title[mPosition]);
        adapter.addFragment(mr, "Review");
        adapter.addFragment(new MovieLocationFragment(), "Location");
        viewPager.setAdapter(adapter);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {

            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {

            return mFragmentList.size();

        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);

        }
    }

    private void setTabIcons(boolean reset) {

        if (reset) {
            detail_nav_tab.getTabAt(0).setIcon(R.drawable.home_click);
        } else {
            detail_nav_tab.getTabAt(0).setIcon(R.drawable.home);
        }
        detail_nav_tab.getTabAt(1).setIcon(R.drawable.review);
        detail_nav_tab.getTabAt(2).setIcon(R.drawable.location);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabIcons(false);
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.home_click);
                break;
            case 1:
                tab.setIcon(R.drawable.review_click);
                break;
            case 2:
                tab.setIcon(R.drawable.location_click);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public AssetManager getAssets() {
        return getResources().getAssets();
    }


}
