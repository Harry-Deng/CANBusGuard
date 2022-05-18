package com.dengemo.TekWulf.CANBusGuardian.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dengemo.TekWulf.CANBusGuardian.room.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM user WHERE uid = :uid")
    User getByUId(int uid);

    @Query("SELECT * FROM user WHERE username = :username")
    User getByUsername(String username);

    @Query("SELECT password FROM user WHERE username = :username")
    String getPasswordByUsername(String username);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(List<User> users);

    @Update
    void update(User user);
}