package com.company.roomdatabasesample;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "store")
public class RoomEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @ColumnInfo(name = "age")
    private String age;



    public RoomEntity(String word, String age) {
        this.mWord = word;
        this.age=age;
    }

    public String getWord() {
        return this.mWord;
    }


    public String  getAge() {
        return age;
    }
}
