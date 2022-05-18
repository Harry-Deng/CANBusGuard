package com.dengemo.TekWulf.CANBusGuardian.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName =  "user")
public class User{
    @PrimaryKey(autoGenerate = true) // 设置为主键自动增长
    @NonNull
    public int uid;

    @ColumnInfo(name = "username")
    @NonNull
    public String username;

    @ColumnInfo(name = "password")
    @NonNull
    public String password;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

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
