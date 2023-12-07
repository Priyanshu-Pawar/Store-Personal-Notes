package com.demo.roomdatabase.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserTable.class, UserNotes.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {


    public abstract UserDao userDao();
    public abstract UserNotesDao userNotesDao();

    private static volatile UserDatabase INSTANCE;

    @NonNull
    public static UserDatabase getDatabase(Context context) {
        UserDatabase tempInstance = INSTANCE;
        if (tempInstance != null) {
            return tempInstance;
        }

        synchronized (UserDatabase.class) {
            UserDatabase instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    "userDatabase"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

            INSTANCE = instance;
            return instance;
        }
    }
}
