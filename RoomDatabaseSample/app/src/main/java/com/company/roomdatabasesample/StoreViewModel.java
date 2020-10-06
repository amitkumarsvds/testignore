package com.company.roomdatabasesample;

import android.app.Application;

import org.w3c.dom.Entity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StoreViewModel extends AndroidViewModel {


    private final Application mContext;
    private RoomDataBaseMain mRoomDataBaseMain;
    private RoomDao mWordDao;

    public StoreViewModel(@NonNull Application application) {
        super(application);

        mContext=application;
        mRoomDataBaseMain= RoomDataBaseMain.getDatabase(mContext);
        mWordDao= mRoomDataBaseMain.wordDao();
    }

    public void storeData(String name)
    {

        RoomEntity entity=new RoomEntity(name,"23");
        mWordDao.insert(entity);

    }

    public LiveData<List<RoomEntity>> getStoredata()
    {

        return mWordDao.getAlphabetizedWords();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
