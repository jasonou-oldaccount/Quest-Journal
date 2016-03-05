package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.Firebase;

public class QuestTabCreateTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab_create_tab);
    }

    public void createQuest(View view) {
        String ref_quest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests/";
        Firebase newQuest = new Firebase(ref_quest);

        QuestObject createQuest = new QuestObject("Quest 1", 1, "This is the first quest", 01012016);

        newQuest.push().setValue(createQuest);
    }
}
