package com.example.helloworld;

import androidx.room.Room;
import android.content.Context;

public class AppDatabaseSingleton {

    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "user-database")
                    .build();
        }
        return db;
    }
}
