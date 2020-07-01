package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.musicapp.Model.QuangCao;
import com.example.musicapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DanhsachbaihatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;

    QuangCao quangCao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        DateIntent();
        anhxa();
//        init();
    }

//    private void init() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
//    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat = findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
    }

    private void DateIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangCao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}