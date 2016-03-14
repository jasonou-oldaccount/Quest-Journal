package com.example.messiah.questjournal;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

import com.example.messiah.questjournal.QuestTab.QuestTabActivity;
import com.firebase.client.Firebase;

public class CharacterActivity extends TabActivity {

    Firebase ref;
    public static MediaPlayer myMusic;
    public static MediaPlayer mySound;
    boolean firstLoad = true;


//    boolean prefSound;
//    boolean prefMusic;
//
//    public void loadSavedPreferences() {
//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(this);
//        prefMusic = sharedPreferences.getBoolean("setSound", true);
//        prefSound = sharedPreferences.getBoolean("setMusic", true);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_character);
        Firebase.setAndroidContext(this);

        ref = MainActivity.ref;

        final TabHost mTabHost;
        mTabHost = getTabHost();
        mySound = MediaPlayer.create(getApplicationContext(), R.raw.pop);


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(firstLoad){
                    firstLoad = !firstLoad;
                    return;
                }
                if (mySound.isPlaying()) {
                    mySound.stop();
                    mySound.release();
                    mySound = MediaPlayer.create(getApplicationContext(), R.raw.pop);
                }
                if(SettingTabActivity.prefSound)
                    mySound.start();
                Log.i("debug", "" + mTabHost.getCurrentTab());
            }
        });

        mTabHost.getTabWidget().setStripEnabled(false);

        mTabHost.addTab(mTabHost
                .newTabSpec("character")
                .setIndicator("", getResources().getDrawable(R.drawable.character_tab_icon))
                .setContent(new Intent(this, CharacterTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("quest")
                .setIndicator("", getResources().getDrawable(R.drawable.quest_tab_icon))
                .setContent(new Intent(this, QuestTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("shop")
                .setIndicator("", getResources().getDrawable(R.drawable.shop_tab_icon))
                .setContent(new Intent(this, ShopTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("Achieve")
                .setIndicator("", getResources().getDrawable(R.drawable.achievement_tab_icon))
                .setContent(new Intent(this, AchievementTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("Setting")
                .setIndicator("", getResources().getDrawable(R.drawable.setting_tab_icon))
                .setContent(new Intent(this, SettingTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.setCurrentTab(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myMusic.release();
    }

    @Override
    protected void onResume() {
        myMusic = MediaPlayer.create(this, R.raw.ninja);
        myMusic.setVolume(0f, .6f);

        if(SettingTabActivity.prefMusic) {
            Log.i("debug", "musicOn");
            myMusic.start();
        }else Log.i("debug", "musicOff");



        super.onResume();
    }
}
