package com.example.shaimaaderbaz.thenewsapp.views.view;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;

import java.util.List;

/**
 * Created by Shaimaa Derbaz on 7/21/2019.
 */

public interface NewsView {
    void showArticles(List<Article> articles);

    void showError(String error);

    void showProgress();

    void hideProgress();
}
