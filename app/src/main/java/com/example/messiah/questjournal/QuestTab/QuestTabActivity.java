package com.example.messiah.questjournal.QuestTab;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.messiah.questjournal.R;
import com.example.messiah.questjournal.SettingTabActivity;

public class QuestTabActivity extends TabActivity {

    public static Typeface type;

    public static MediaPlayer mySound;
    boolean firstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab);

        TabHost qTabHost = getTabHost();

        mySound = MediaPlayer.create(getApplicationContext(), R.raw.pop);
        qTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (firstLoad) {
                    firstLoad = !firstLoad;
                    return;
                }
                if (mySound.isPlaying()) {
                    mySound.stop();
                    mySound.release();
                    mySound = MediaPlayer.create(getApplicationContext(), R.raw.pop);
                }
                if (SettingTabActivity.prefSound)
                    mySound.start();
//                Log.i("debug", "" + qTabHost.getCurrentTab());
            }
        });

        qTabHost.getTabWidget().setStripEnabled(false);

        qTabHost.addTab(qTabHost
                .newTabSpec("View")
                .setIndicator("N E W")
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

        type = Typeface.createFromAsset(getAssets(),"fonts/pixel_font.ttf");

        for(int i=0; i<qTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) qTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTypeface(type);
            tv.setTextSize(15);
        }
    }

}
