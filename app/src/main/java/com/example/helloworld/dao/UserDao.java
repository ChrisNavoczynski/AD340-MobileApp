package com.example.helloworld.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.example.helloworld.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getSettings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateSettings(User... users);
}
