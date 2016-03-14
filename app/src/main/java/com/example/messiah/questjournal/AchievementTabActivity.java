package com.example.messiah.questjournal;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AchievementTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_tab);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/pixel_font.ttf");

        TextView ach_message = (TextView) findViewById(R.id.achievement_coming_soon);
        ach_message.setTypeface(type);
    }
}
