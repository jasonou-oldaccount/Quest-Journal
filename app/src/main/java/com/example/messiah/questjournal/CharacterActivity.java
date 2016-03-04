package com.example.messiah.questjournal;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class CharacterActivity extends TabActivity {

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        Firebase.setAndroidContext(this);

        ref = MainActivity.ref;

        TabHost mTabHost = getTabHost();

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

    public void logOut(View view){
        ref.unauth();
        Toast.makeText(getApplicationContext(), "logged out", Toast.LENGTH_SHORT).show();
        Log.i("auth", "logged out");
        startActivity(new Intent(this, MainActivity.class));
    }
}
