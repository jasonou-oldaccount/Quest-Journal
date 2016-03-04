package com.example.messiah.questjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

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
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.achievement_tab));
        tabSpec.setContent(R.id.achievement_tab);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Set");
        tabSpec.setContent(R.id.setting_tab);
        tabSpec.setIndicator("Set");
        tabHost.addTab(tabSpec);
    }

    public void logOut(View view){
        ref.unauth();
        Toast.makeText(getApplicationContext(), "logged out", Toast.LENGTH_SHORT).show();
        Log.i("auth", "logged out");
        startActivity(new Intent(this, MainActivity.class));

    }
}
