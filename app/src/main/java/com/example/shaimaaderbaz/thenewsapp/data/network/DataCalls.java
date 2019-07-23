package com.example.shaimaaderbaz.thenewsapp.data.network;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.data.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Shaimaa Derbaz on 7/23/2019.
 */

public class DataCalls {
    private NewsAPI newsAPI;

    public DataCalls() {
        newsAPI = Utils.getNewsAPI();
    }

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
//
}
