package com.dengemo.TekWulf.CANBusGuardian.room.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dengemo.TekWulf.CANBusGuardian.room.dao.UserDao;
import com.dengemo.TekWulf.CANBusGuardian.room.entity.User;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static UserDatabase INSTANCE;

    public static UserDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, UserDatabase.class, "userDatabase_room").
                            fallbackToDestructiveMigration().
                            build();
                }
            }
        }
        return INSTANCE;
    }



}