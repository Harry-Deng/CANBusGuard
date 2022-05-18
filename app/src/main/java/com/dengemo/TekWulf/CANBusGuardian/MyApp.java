package com.dengemo.TekWulf.CANBusGuardian;


import android.app.Application;
import androidx.room.Room;

import com.dengemo.TekWulf.CANBusGuardian.room.database.UserDatabase;

public class MyApp extends Application {
    public static Application application;
    public static UserDatabase userDatabase;



    @Override
    public void onCreate() {

        super.onCreate();
        application = this;

        // ROOM数据库
        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "userDatabase_room").build();

    }



}
