package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class CharacterActivity extends AppCompatActivity {

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        Firebase.setAndroidContext(this);

        ref = MainActivity.ref;
    }
}
