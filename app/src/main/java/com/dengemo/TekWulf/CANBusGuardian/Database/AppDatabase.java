package com.dengemo.TekWulf.CANBusGuardian.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase CANBusGuardianDatabase;

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (CANBusGuardianDatabase == null){
            synchronized (AppDatabase.class) {
                if (CANBusGuardianDatabase == null) {
                    CANBusGuardianDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "CANBusGuardianDb.db")
                            .addMigrations()
                            // 默认不允许在主线程中连接数据库
                            // .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return CANBusGuardianDatabase;
    }
}
