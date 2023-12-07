package com.demo.roomdatabase.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(UserTable userTable);

    @Query("SELECT EXISTS(SELECT * FROM user_table_for_login WHERE userEmail = :userEmail)")
    boolean is_taken(String userEmail);

    @Query("SELECT EXISTS (SELECT * FROM user_table_for_login WHERE userEmail = :userEmail AND  password = :password)")
    boolean login(String userEmail, String password);
}
