package com.example.musicapp.Services;

public class APIService {
    private static String base_url = "https://aluminous-engineeri.000webhostapp.com/server/";

    public static DataService getService(){


        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}
