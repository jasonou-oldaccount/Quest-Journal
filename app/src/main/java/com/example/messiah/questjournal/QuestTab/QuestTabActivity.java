package com.example.messiah.questjournal.QuestTab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.messiah.questjournal.R;

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

}
