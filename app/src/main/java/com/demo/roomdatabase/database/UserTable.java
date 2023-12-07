package com.demo.roomdatabase.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table_for_login")
public class UserTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userEmail")
    private String userEmail;
    @ColumnInfo(name = "password")
    private String password;

    public UserTable(int id, String userEmail, String password) {
        this.id = id;
        this.userEmail = userEmail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


