package com.example.shaimaaderbaz.thenewsapp.data.network;

import com.example.shaimaaderbaz.thenewsapp.data.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shaimaa Derbaz on 7/22/2019.
 */

public interface NewsAPI {


    @GET("/v2/top-headlines?country=us&category=business&apiKey=e313174072f94a43812840d8c906c028")
    Call<Response> getAllNewsData();


}
