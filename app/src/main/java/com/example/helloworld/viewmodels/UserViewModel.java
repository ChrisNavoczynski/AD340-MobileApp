package com.example.helloworld.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.helloworld.entity.User;
import com.example.helloworld.AppDatabase;
import com.example.helloworld.AppDatabaseSingleton;

import java.util.List;


public class UserViewModel extends ViewModel {

    public LiveData<List<User>> loadSettings(Context context) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.userDao().getSettings();
    }

    public void updateSettings(Context context, User... users) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> db.userDao().updateSettings(users));
    }

    public void deleteSettings(Context context) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(db::clearAllTables);
    }
}
