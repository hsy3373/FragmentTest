package com.gom.test.data.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.gom.test.ui.UserHandler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DatabaseWrapper {
    private static DatabaseWrapper instance;
    AppDatabase db;
    private static ExecutorService executorService;

    private DatabaseWrapper(Context context){
        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_db").build();
        executorService = Executors.newSingleThreadExecutor();
    }

    public static DatabaseWrapper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseWrapper(context);
        }
        return instance;
    }

    public void insertUser(User user) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.userDao().insert(user);
            }
        });
    }

    public void clearTable() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.userDao().deleteAll();
            }
        });
    }

    public void getAllUsers(UserHandler handler) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (handler != null) {
                    handler.onResult(db.userDao().getAll());
                }
            }
        });
    }
}
