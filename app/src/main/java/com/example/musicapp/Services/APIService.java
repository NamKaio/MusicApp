package com.example.musicapp.Services;

public class APIService {
    private static String base_url = "http://10.0.2.2/MusicAppAndroid/Server/";

    public static DataService getService(){


        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}
