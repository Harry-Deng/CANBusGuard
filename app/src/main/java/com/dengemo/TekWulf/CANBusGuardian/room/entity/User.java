package com.dengemo.TekWulf.CANBusGuardian.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName =  "user", indices = {@Index(value = {"username"}, unique = true)})
public class User{
    @PrimaryKey
    @ColumnInfo(name = "username")
    @NonNull
    public String username;



    @ColumnInfo(name = "password")
    @NonNull
    public String password;

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
