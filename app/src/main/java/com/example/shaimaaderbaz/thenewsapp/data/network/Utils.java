package com.example.shaimaaderbaz.thenewsapp.data.network;

/**
 * Created by Shaimaa Derbaz on 7/22/2019.
 */

public class Utils {

    public static final String BASE_URL = "https://newsapi.org";

    public static NewsAPI getNewsAPI() {
        return RetrofitClient.getClient(BASE_URL).create(NewsAPI.class);
    }
}
