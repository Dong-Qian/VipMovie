/*
* FILE :            MovieActivity.java
* PROJECT :         MAD - Assignment #1
* PROGRAMMER :      Dong Qian (6573448) / Austin Che (7243322) / Monira Sultana (7308182)
* FIRST VERSION :   2017-01-25
* DESCRIPTION :     Detail Screen with the specific Movies.
*                   Implement the ViewPaper which contains 3 fragments
*                       1. WebView connect to the IMDB
*                       2. A Review Screen
*                       3  A about Screen
*/


package com.qiandongyqgmail.vipmovie;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


public class MovieActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener {

    /*-PRIVATE ATTRIBUTES-*/
    private int mPosition = 0;
    private String[] url = null;
    private String[] title = null;
    private ViewPager detail_content_viewPager = null;
    private TabLayout detail_nav_tab = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        // Get the movie Position which pass from MainActivity in order to know which movie it is
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("title", 0);
        // According to the position, pull put movie url and title from array.xml
        Resources resources = getResources();
        url = resources.getStringArray(R.array.movie_url);
        title = resources.getStringArray(R.array.movie_title);

        // Link the ViewPaper variables to the widgets
        detail_content_viewPager = (ViewPager) findViewById(R.id.movie_detail_viewpager);
        setupViewPager(detail_content_viewPager);
        // Link the TabLayout to the widgets
        detail_nav_tab = (TabLayout) findViewById(R.id.movie_nav_tabs);
        detail_nav_tab.setupWithViewPager(detail_content_viewPager);
        // Setup the tabSelectedListener to check which tab is current selected
        detail_nav_tab.addOnTabSelectedListener(this);
        // Set the tab icon
        setTabIcons(true);
    }


    // ViewPaper is a container for the View (each fragment)
    // We use Adapter to link each fragment with specific tab and show in the ViewPager
    private void setupViewPager(ViewPager viewPager) {
        // Create a adapter
        Adapter adapter = new Adapter(getSupportFragmentManager());
        // Create MovieDetailFragment and add to the tab1
        MovieDetailFragment md = new MovieDetailFragment();
        md.setParameter(url[mPosition]);
        adapter.addFragment(md, "Detail");
        // Create MovieReviewFragment and add to the tab2
        MovieReviewFragment mr = new MovieReviewFragment();
        mr.setParameter(title[mPosition]);
        adapter.addFragment(mr, "Review");
        // Create MovieLocationFragment and add to the tab2
        adapter.addFragment(new MovieLocationFragment(), "Location");
        // Link the tab to the ViewPager
        viewPager.setAdapter(adapter);
    }


    // Adapter for fragment
    // Create a List to contain all the fragments
    static class Adapter extends FragmentStatePagerAdapter {
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

        // Function to add fragments into a fragment list
        // ViewPager can use this adapter to setup each view
        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);

        }
    }


    // Function to set the tab icon from the drawable resources
    private void setTabIcons(boolean reset) {

        if (reset) {
            detail_nav_tab.getTabAt(0).setIcon(R.drawable.home_click);
        } else {
            detail_nav_tab.getTabAt(0).setIcon(R.drawable.home);
        }
        detail_nav_tab.getTabAt(1).setIcon(R.drawable.review);
        detail_nav_tab.getTabAt(2).setIcon(R.drawable.location);

    }


    // Function used to change the icon when the tab is selected
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

    // Needs to override when use the Tab
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    // Needs to override when use the Tab
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
