package com.example.musicapp.Services;

import android.provider.MediaStore;

import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.ChuDeTheLoai;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.QuangCao;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();
}
