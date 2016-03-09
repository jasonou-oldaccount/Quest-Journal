package com.example.messiah.questjournal;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class CharacterTabActivity extends AppCompatActivity {

    ImageButton char_button;

    public static long exp;
    public static String refExp = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/exp";
    public static Firebase curExp = new Firebase(refExp);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_tab);

        String nickname_ref = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/nickname";
        Firebase ref = new Firebase(nickname_ref);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                TextView textview_nickname = (TextView) findViewById(R.id.character_nickname);
                textview_nickname.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        curExp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.i("debug", refExp);
                exp = (long) snapshot.getValue();
                TextView textview_exp = (TextView) findViewById(R.id.exp_view);
                String expString = String.valueOf(exp);
                textview_exp.setText(expString);

                int level = (Integer.valueOf(expString))/25;
                TextView textView_level = (TextView) findViewById(R.id.level_view);

                textView_level.setText("Level " + level);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        char_button = (ImageButton) findViewById(R.id.character_image);
        // char_button.setOnTouchListener(this);
    }

    /* float x, y = 0.0f;
    boolean moving = false;

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        switch(arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving) {
                    x = arg1.getRawX() - char_button.getWidth()/2;
                    y = arg1.getRawY() - char_button.getHeight()/2;
                    char_button.setX(x);
                    char_button.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    } */
}
