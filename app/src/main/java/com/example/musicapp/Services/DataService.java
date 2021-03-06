package com.example.musicapp.Services;

import android.provider.MediaStore;

import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.Model.ChuDe;
import com.example.musicapp.Model.ChuDeTheLoai;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.QuangCao;
import com.example.musicapp.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetDanhSachPlaylist();

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetDanhSachAllChuDe();

    @GET("tatcaalbum.php")
    Call<List<Album>> GetDanhSachAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);


    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Album>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updatelike.php")
    Call<String> GetUpdateLike(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHat>> GetSearch(@Field("tukhoa") String tukhoa);

}
