package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.firebase.client.Firebase;

public class CharacterActivity extends AppCompatActivity {

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        Firebase.setAndroidContext(this);

        ref = MainActivity.ref;

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Char");
        tabSpec.setContent(R.id.character_tab);
        tabSpec.setIndicator("Char");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Qst");
        tabSpec.setContent(R.id.quest_tab);
        tabSpec.setIndicator("Qst");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Shp");
        tabSpec.setContent(R.id.shop_tab);
        tabSpec.setIndicator("Shp");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Ach");
        tabSpec.setContent(R.id.achievement_tab);
        tabSpec.setIndicator("Ach");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Set");
        tabSpec.setContent(R.id.setting_tab);
        tabSpec.setIndicator("Set");
        tabHost.addTab(tabSpec);
    }
}
