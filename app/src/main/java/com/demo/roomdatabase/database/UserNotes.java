package com.demo.roomdatabase.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_notes")
public class UserNotes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userName")
    private String userName;
    @ColumnInfo(name = "userNotes")
    private String userNotes;

    @ColumnInfo(name = "bookmarkItem")
    private Boolean bookmarkItem;

    public UserNotes( int id,  String userName,  String userNotes,  Boolean bookmarkItem) {
        this.id = id;
        this.userName = userName;
        this.userNotes = userNotes;
        this.bookmarkItem = bookmarkItem;
    }

    public Boolean getBookmarkItem() {
        return bookmarkItem;
    }

    public void setBookmarkItem(Boolean bookmarkItem) {
        this.bookmarkItem = bookmarkItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
