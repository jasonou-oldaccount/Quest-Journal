package com.example.messiah.questjournal;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;

import com.firebase.client.Firebase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuestTabActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab);

        TabHost qTabHost = getTabHost();

        qTabHost.getTabWidget().setStripEnabled(false);

        qTabHost.addTab(qTabHost
                .newTabSpec("View")
                .setIndicator("View")
                .setContent(new Intent(this, QuestTabViewTab.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        qTabHost.addTab(qTabHost
                .newTabSpec("Old")
                .setIndicator("Old")
                .setContent(new Intent(this, QuestTabOldTab.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        qTabHost.addTab(qTabHost
                .newTabSpec("Create")
                .setIndicator("Create")
                .setContent(new Intent(this, QuestTabCreateTab.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        qTabHost.setCurrentTab(0);
    }

    public void createQuest(View view) {
        String ref_quest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests/";
        Firebase newQuest = new Firebase(ref_quest);

        QuestObject createQuest = new QuestObject("Quest 1", 1, "This is the first quest", 01012016);

        newQuest.push().setValue(createQuest);
    }

}
