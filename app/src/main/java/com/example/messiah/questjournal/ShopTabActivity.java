package com.example.messiah.questjournal;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShopTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tab);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/pixel_font.ttf");

        TextView shop_message = (TextView) findViewById(R.id.shop_coming_soon);
        shop_message.setTypeface(type);
    }
}
