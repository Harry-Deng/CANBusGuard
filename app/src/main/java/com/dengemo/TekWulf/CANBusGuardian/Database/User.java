package com.dengemo.TekWulf.CANBusGuardian.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName =  "user")
public class User {
    @PrimaryKey(autoGenerate = true) // 设置为主键自动增长
    public int uid;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;
}
