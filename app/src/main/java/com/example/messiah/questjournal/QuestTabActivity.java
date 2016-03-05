package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab);
    }

    public void saveQuest(View view){
        String ref_quest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID
    }

}
