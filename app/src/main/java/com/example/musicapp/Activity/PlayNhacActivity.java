package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac;
    TextView txtTimeSong, txttotalTimeSong;
    ImageView imgPlay, imgRepeat, imgPre, imgNext, imgRandom;
    ViewPager viewPagerPlayNhac;

    public static ArrayList<BaiHat> baiHatArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        baiHatArrayList.clear();

        if (intent != null){
            if (intent.hasExtra("nhac")) {
                BaiHat baiHat = intent.getParcelableExtra("nhac");
                baiHatArrayList.add(baiHat);
            }
            if (intent.hasExtra("tatcanhac")) {
                ArrayList<BaiHat> tatCaNhacArrayList = intent.getParcelableArrayListExtra("tatcanhac");
                baiHatArrayList = tatCaNhacArrayList;
            }
        }

    }

    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarplaynhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txttotalTimeSong = findViewById(R.id.textviewtotaltimesong);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgRepeat = findViewById(R.id.imagebuttonrepeat);
        imgPre = findViewById(R.id.imagebuttonpre);
        imgNext = findViewById(R.id.imagebuttonnext);
        imgRandom = findViewById(R.id.imagebuttonshuffle);
        viewPagerPlayNhac = findViewById(R.id.viewpagerplaynhac);

        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
    }
}