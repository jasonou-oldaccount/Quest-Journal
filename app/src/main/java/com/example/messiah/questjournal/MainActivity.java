package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
//shikaaaaaaaa
    // Firebase ref used to reference our Firebase backend
    public static Firebase ref;

    //on create establish firebase connection
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the Android Context for Firebase
        Firebase.setAndroidContext(this);

        // Creates a new Firebase ref using specified path
        ref = new Firebase("https://questjournal.firebaseio.com/");
    }
}
