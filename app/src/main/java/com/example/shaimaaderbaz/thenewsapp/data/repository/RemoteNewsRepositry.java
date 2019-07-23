package com.example.shaimaaderbaz.thenewsapp.data.repository;


import android.content.Context;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.data.model.Response;
import com.example.shaimaaderbaz.thenewsapp.data.network.AllNewsCall;
import com.example.shaimaaderbaz.thenewsapp.data.network.NewsAPI;
import com.example.shaimaaderbaz.thenewsapp.data.network.Utils;
import com.example.shaimaaderbaz.thenewsapp.views.view.NewsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Shaimaa Derbaz on 4/9/2019.
 */

public class RemoteNewsRepositry implements Repository<Article> {
    List<Article> data = new ArrayList<>();
    private NewsAPI newsAPI;


    public RemoteNewsRepositry(Context context) { //not sure
        newsAPI = Utils.getNewsAPI();

    }

    public List<Article> getData() {

        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

    @Override
    public void getAllNews(final AllNewsCall presenterCallback) {

        Call<Response> call = newsAPI.getAllNewsData();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body() != null) {
                    List<Article> allArticles = response.body().getArticles();
                    presenterCallback.success(allArticles);

                } else
                    presenterCallback.error("Unknown Error");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                presenterCallback.error(t.getMessage());

            }
        });
    }

    @Override
    public void putNews(List<Article> articles) {

    }
    @Override
    public  void deleteAllNews() {

    }

}
