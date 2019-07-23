package com.example.shaimaaderbaz.thenewsapp.data.network;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;

import java.util.List;

/**
 * Created by Shaimaa Derbaz on 7/23/2019.
 */
public interface AllNewsCall {
    void success(List<Article> articles);

    void error(String error);
}
