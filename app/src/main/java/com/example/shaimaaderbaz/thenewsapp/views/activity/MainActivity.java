package com.example.shaimaaderbaz.thenewsapp.views.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shaimaaderbaz.thenewsapp.R;
import com.example.shaimaaderbaz.thenewsapp.data.database.AppDB;
import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.data.repository.LocalNewsRepositry;
import com.example.shaimaaderbaz.thenewsapp.data.repository.RemoteNewsRepositry;
import com.example.shaimaaderbaz.thenewsapp.presenters.MainPresenterImp;
import com.example.shaimaaderbaz.thenewsapp.views.adapter.NewsAdapter;
import com.example.shaimaaderbaz.thenewsapp.views.view.NewsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NewsView,
        NewsAdapter.NewsItemListener {

    @BindView(R.id.recycler_news)
    RecyclerView newsRecyclerView;

    private MainPresenterImp mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppDB mAppDb=AppDB.getInstance(this);
        mPresenter = new MainPresenterImp(this, new RemoteNewsRepositry(this), new LocalNewsRepositry(mAppDb));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getAllNews();
    }

    @Override
    public void showArticles(List<Article> articles) {
        newsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        NewsAdapter newsAdapter = new NewsAdapter(articles, this);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onItemClicked(Article article) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(article.getUrl()));
    }

    @Override
    public void onItemSwiped(Article article) {
        Toast.makeText(this,article.getTitle(),Toast.LENGTH_LONG).show();
    }
    @Override
    public void onItemLongClicked(Article article) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        startActivity(Intent.createChooser(i, "Share URL"));    }
}
