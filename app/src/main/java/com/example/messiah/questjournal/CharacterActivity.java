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
                .setIndicator("Char")
                .setContent(new Intent(this, CharacterTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("quest")
                .setIndicator("Quest")
                .setContent(new Intent(this, QuestTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("shop")
                .setIndicator("Shop")
                .setContent(new Intent(this, ShopTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("Achieve")
                .setIndicator("Achieve")
                .setContent(new Intent(this, AchievementTabActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        mTabHost.addTab(mTabHost
                .newTabSpec("Setting")
                .setIndicator("Setting")
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
