package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.Model.ChuDe;
import com.example.musicapp.R;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbarTheLoaiTheoChuDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_theloai_theo_chude);
        GetIntent();
        init();


    }

    private void init() {
        recyclerViewTheLoaiTheoChuDe = findViewById(R.id.recycleviewtheloaitheochude);
        toolbarTheLoaiTheoChuDe = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbarTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this,chuDe.getTenChuDe(),Toast.LENGTH_SHORT).show();
        }
    }
}