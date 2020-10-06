package com.company.roomdatabasesample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RoomDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RoomEntity word);

    @Query("DELETE FROM store")
    void deleteAll();

    @Query("SELECT * from store ORDER BY word ASC")
    LiveData<List<RoomEntity>> getAlphabetizedWords();
}
