package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuestTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab);
    }

    public void createQuest(View view){
        String ref_quest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests/";
        Firebase newQuest = new Firebase(ref_quest);

        

        EditText title_quest = (EditText) findViewById(R.id.title_input);
        EditText difficulty_quest = (EditText) findViewById(R.id.title_input);
        EditText title_quest = (EditText) findViewById(R.id.title_input);
        EditText title_quest = (EditText) findViewById(R.id.title_input);

        QuestObject createQuest = new QuestObject("Quest 1", 1, "This is the first quest", 01012016);



        newQuest.push().setValue(createQuest);



    }

}
