package com.company.roomdatabasesample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StoreView extends AppCompatActivity {

    private EditText mEdtName;
    private Button mBtnName;
    private TextView mTxtName;
    private StoreViewModel mViewModelStore;
    private String getName;
    private String getNameRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUiContent();

        mViewModelStore = ViewModelProviders.of(StoreView.this).get(StoreViewModel.class);

        mBtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getName = mEdtName.getText().toString();
                if (getName.isEmpty() || getName == null) {

                    Toast.makeText(StoreView.this, "Please enter name", Toast.LENGTH_LONG).show();
                } else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mViewModelStore.storeData(getName);
                        }
                    }).start();

                    //Logic
                }
            }
        });

        mViewModelStore.getStoredata().observe(this, new Observer<List<RoomEntity>>() {
            @Override
            public void onChanged(List<RoomEntity> roomEntities) {
                if (roomEntities != null && roomEntities.size() > 0) {
                    for (int i = 0; i < roomEntities.size(); i++) {
                        getNameRoom = getNameRoom+','+roomEntities.get(i).getWord();
                    }

                   // mTxtName.setText(getNameRoom);
                    mTxtName.setText(roomEntities.get(0).getAge());
                }
            }
        });

    }

    private void setUiContent() {
        mEdtName = findViewById(R.id.edtName);
        mBtnName = findViewById(R.id.btnSave);
        mTxtName = findViewById(R.id.txtDisplay);
    }
}
