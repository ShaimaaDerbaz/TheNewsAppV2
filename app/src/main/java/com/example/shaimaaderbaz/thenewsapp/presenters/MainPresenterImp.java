package com.example.shaimaaderbaz.thenewsapp.presenters;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.data.network.AllNewsCall;
import com.example.shaimaaderbaz.thenewsapp.data.repository.Repository;
import com.example.shaimaaderbaz.thenewsapp.views.view.NewsView;

import java.util.List;


/**
 * Created by Shaimaa Derbaz on 7/22/2019.
 */

public class MainPresenterImp implements MainPresenter<Article> {
    private NewsView mView;

    private Repository msRepository;

    private Repository mLocalRepository;

    AllNewsCall presenterCallbackLocal = new AllNewsCall() {
        @Override
        public void success(List<Article> articles) {
            mView.showArticles(articles);
            mView.hideProgress();
        }

        @Override
        public void error(String error) {
            mView.showError("No Iternet , No Local Data .");
            mView.hideProgress();
        }
    };
    AllNewsCall presenterCallback = new AllNewsCall() {
        @Override
        public void success(List<Article> articles) {
            mView.hideProgress();
            mView.showArticles(articles);
            mLocalRepository.deleteAllNews();
            mLocalRepository.putNews(articles);
        }

        @Override
        public void error(String error) {
            mView.hideProgress();
            mView.showError("No Internet , get Local data from DB .");
            mLocalRepository.getAllNews(presenterCallbackLocal);
        }
    };


    public MainPresenterImp(NewsView newsView, Repository spr, Repository sprLocal) {
        mView = newsView;
        msRepository = spr;
        mLocalRepository = sprLocal;
    }

    @Override
    public void getAllNews() {
        mView.showProgress();
        msRepository.getAllNews(presenterCallback);
    }
}
