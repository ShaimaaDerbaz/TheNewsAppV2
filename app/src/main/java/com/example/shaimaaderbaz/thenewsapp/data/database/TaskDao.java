package com.example.shaimaaderbaz.thenewsapp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;

import java.util.List;

/**
 * Created by Shaimaa Derbaz on 7/23/2019.
 */

@Dao
public interface TaskDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Insert
    void insertItem(Article article);

    @Delete
    void delete(Article article);

    @Query("DELETE FROM article ")
    void deleteAll();
}

