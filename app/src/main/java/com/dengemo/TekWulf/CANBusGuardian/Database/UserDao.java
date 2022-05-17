package com.dengemo.TekWulf.CANBusGuardian.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> doFindAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    LiveData<List<User>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE username IN (:username)")
    User doFindByUsername(String username);

    @Insert
    void insert(User... users);

    @Delete
    void delete(User... user);

    @Update
    void update(User... user);

    @Query("DELETE FROM user")
    void delAllUser();
}