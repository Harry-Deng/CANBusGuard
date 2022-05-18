package com.dengemo.TekWulf.CANBusGuardian.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dengemo.TekWulf.CANBusGuardian.room.dao.UserDao;
import com.dengemo.TekWulf.CANBusGuardian.room.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
