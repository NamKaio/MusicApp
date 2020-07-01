package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("idAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("TenAlBum")
    @Expose
    private String tenAlBum;
    @SerializedName("TenCaSiAlbum")
    @Expose
    private String tenCaSiAlbum;
    @SerializedName("HinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlBum() {
        return tenAlBum;
    }

    public void setTenAlBum(String tenAlBum) {
        this.tenAlBum = tenAlBum;
    }

    public String getTenCaSiAlbum() {
        return tenCaSiAlbum;
    }

    public void setTenCaSiAlbum(String tenCaSiAlbum) {
        this.tenCaSiAlbum = tenCaSiAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }
}
