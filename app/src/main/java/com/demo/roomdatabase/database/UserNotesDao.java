package com.demo.roomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserNotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(UserNotes userNotes);

    @Query("SELECT * FROM user_notes ORDER BY id ASC")
    LiveData<List<UserNotes>> readAllData();

    @Delete
    void deleteUser(UserNotes userNotes);

    @Update
    void updateUser(UserNotes userNotes);

    @Query("SELECT * FROM user_notes WHERE id = :userId")
    UserNotes getUserById(int userId);


    @Query("SELECT bookmarkItem FROM user_notes WHERE id = :userId")
    Boolean getBookmark(int userId);

    @Query("SELECT * FROM user_notes WHERE bookmarkItem = 1")
    LiveData<List<UserNotes>> getBookmarkedUserNotes();

    @Query("UPDATE user_notes SET bookmarkItem =:bookmark WHERE id = :id ")
    void updateBookmark(Boolean bookmark, int id);


}
