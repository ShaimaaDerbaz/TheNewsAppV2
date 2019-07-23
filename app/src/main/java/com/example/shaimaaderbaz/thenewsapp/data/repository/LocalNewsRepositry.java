package com.example.shaimaaderbaz.thenewsapp.data.repository;

import android.os.AsyncTask;

import com.example.shaimaaderbaz.thenewsapp.data.database.AppDB;
import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.data.network.AllNewsCall;

import java.util.List;

/**
 * Created by Shaimaa Derbaz on 4/9/2019.
 */

public class LocalNewsRepositry implements Repository<Article> {
    private AppDB mAppDB;

    public LocalNewsRepositry(AppDB appDB) {
        mAppDB = appDB;
    }

    @Override
    public void getAllNews(final AllNewsCall presenterCallback) {
        AsyncTask<Void, Void, List<Article>> asyncTask = new AsyncTask<Void, Void, List<Article>>() {
            @Override
            protected List<Article> doInBackground(Void... voids) {
                return mAppDB.taskDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Article> articles) {
                presenterCallback.success(articles);
            }
        };
        asyncTask.execute();
    }

    @Override
    public void putNews(List<Article> articles) {
        AsyncTask<List<Article>, Void, Void> asyncTask = new AsyncTask<List<Article>, Void, Void>() {
            @SafeVarargs
            @Override
            protected final Void doInBackground(List<Article>... lists) {
                mAppDB.taskDao().insertAll(lists[0]);
                return null;
            }
        };
        asyncTask.execute(articles);

    }

    @Override
    public void deleteAllNews() {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mAppDB.taskDao().deleteAll();
                return null;
            }


        };
        asyncTask.execute();
    }
}
