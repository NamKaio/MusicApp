package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.musicapp.Adapter.MainViewPagerAdapter;
import com.example.musicapp.Fragment.FragmentHome;
import com.example.musicapp.Fragment.FragmentSearch;
import com.example.musicapp.R;
import com.google.android.material.tabs.TabLayout;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentHome(), "Home");
        mainViewPagerAdapter.addFragment(new FragmentSearch(), "Search");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);

    }

    private void mapping() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.MyViewPaper);
        circleIndicator = findViewById(R.id.indicatordefault);
    }
}