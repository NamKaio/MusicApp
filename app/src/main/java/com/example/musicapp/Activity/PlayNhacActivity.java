package com.example.musicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Adapter.ViewPagerPlaylistNhac;
import com.example.musicapp.Fragment.FragmentDanhSachBaiHatPlay;
import com.example.musicapp.Fragment.FragmentDiaNhac;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac;
    TextView txtTimeSong, txttotalTimeSong;
    ImageView imgPlay, imgRepeat, imgPre, imgNext, imgRandom;
    ViewPager viewPagerPlayNhac;
    FragmentDiaNhac fragmentDiaNhac;
    FragmentDanhSachBaiHatPlay fragmentDanhSachBaiHatPlay;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;


    public static ArrayList<BaiHat> baiHatArrayList = new ArrayList<>();
    public static ViewPagerPlaylistNhac viewPagerPlaylistNhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();


    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlaylistNhac.getItem(0) != null) {
                    if (baiHatArrayList.size() > 0) {
                        fragmentDiaNhac.Playnhac(baiHatArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                    fragmentDiaNhac.stopDisc();
                } else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                    fragmentDiaNhac.startDisc();
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkRandom == true) {
                        checkRandom = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                    }
                    imgRepeat.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                } else {
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    checkRandom = false;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baiHatArrayList.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baiHatArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > baiHatArrayList.size() - 1) {
                            position = 0;
                        }
                        new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragmentDiaNhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);

                Handler buttonClickHandle = new Handler();
                buttonClickHandle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);

                    }
                }, 5000);
            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baiHatArrayList.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = baiHatArrayList.size() - 1;
                        }

                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragmentDiaNhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);

                Handler buttonClickHandle = new Handler();
                buttonClickHandle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);

                    }
                }, 5000);
            }
        });


    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        baiHatArrayList.clear();

        if (intent != null) {
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
        seekBar = findViewById(R.id.seekbarsong);
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
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                baiHatArrayList.clear();
            }
        });

        toolbarPlayNhac.setTitleTextColor(Color.WHITE);

        fragmentDiaNhac = new FragmentDiaNhac();
        fragmentDanhSachBaiHatPlay = new FragmentDanhSachBaiHatPlay();

        viewPagerPlaylistNhac = new ViewPagerPlaylistNhac(getSupportFragmentManager());

        viewPagerPlaylistNhac.AddFragmnet(fragmentDiaNhac);
        viewPagerPlaylistNhac.AddFragmnet(fragmentDanhSachBaiHatPlay);

        viewPagerPlayNhac.setAdapter(viewPagerPlaylistNhac);

        fragmentDiaNhac = (FragmentDiaNhac) viewPagerPlaylistNhac.getItem(0);

        if (baiHatArrayList.size() > 0) {
            getSupportActionBar().setTitle(baiHatArrayList.get(0).getTenBaiHat());
            new PlayMp3().execute(baiHatArrayList.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < baiHatArrayList.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baiHatArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > baiHatArrayList.size() - 1) {
                            position = 0;
                        }
                        new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragmentDiaNhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    }

                    imgPre.setClickable(false);
                    imgNext.setClickable(false);

                    Handler buttonClickHandle = new Handler();
                    buttonClickHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPre.setClickable(true);
                            imgNext.setClickable(true);

                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}