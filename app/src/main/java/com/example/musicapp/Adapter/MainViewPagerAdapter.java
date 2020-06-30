package com.example.musicapp.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrFragment = new ArrayList<>();
    private ArrayList<String> arrTitle = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrFragment.size();
    }

    public void addFragment(Fragment fragment, String title) {
        arrFragment.add(fragment);
        arrTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrTitle.get(position);
    }
}
