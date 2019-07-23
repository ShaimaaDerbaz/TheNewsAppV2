package com.example.shaimaaderbaz.thenewsapp.data.repository;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.data.network.AllNewsCall;

import java.util.List;

/**
 * Created by Shaimaa Derbaz on 4/9/2019.
 */

public interface Repository<T> {
    void getAllNews(final AllNewsCall presenterCallback);
    void putNews(List<Article> articles);
    void deleteAllNews();
   }
