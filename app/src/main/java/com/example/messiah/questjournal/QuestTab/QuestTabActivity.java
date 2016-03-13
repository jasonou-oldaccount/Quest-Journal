package com.example.messiah.questjournal.QuestTab;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

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
                .setIndicator("V i e w")
                .setContent(new Intent(this, QuestTabViewTab.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));


        qTabHost.addTab(qTabHost
                .newTabSpec("Old")
                .setIndicator("O l d")
                .setContent(new Intent(this, QuestTabOldTab.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        qTabHost.addTab(qTabHost
                .newTabSpec("Create")
                .setIndicator("C r e a t e")
                .setContent(new Intent(this, QuestTabCreateTab.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        qTabHost.setCurrentTab(0);

        for(int i=0; i<qTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) qTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTypeface(null, Typeface.BOLD);
        }
    }

}
