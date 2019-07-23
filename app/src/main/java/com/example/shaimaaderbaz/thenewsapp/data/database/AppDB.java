package com.example.shaimaaderbaz.thenewsapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.shaimaaderbaz.thenewsapp.data.model.Article;

/**
 * Created by Shaimaa Derbaz on 7/23/2019.
 */
@Database(entities = {Article.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static AppDB INSTANCE = null;

    private static Object lock = new Object();
    public static  AppDB getInstance(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDB.class, "news.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
