package com.company.roomdatabasesample;


import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RoomEntity.class}, version = 2, exportSchema = false)
public abstract class RoomDataBaseMain extends RoomDatabase {

    public abstract RoomDao wordDao();

    private static volatile RoomDataBaseMain INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE store "
                    + " ADD COLUMN age TEXT");
        }
    };

    static RoomDataBaseMain getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDataBaseMain.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDataBaseMain.class, "word_database").

                            addMigrations(migration)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}