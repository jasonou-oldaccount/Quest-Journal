package com.example.messiah.questjournal;

import android.app.Activity;
import android.content.ClipData;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class CharacterTabActivity extends AppCompatActivity {

    ImageButton char_button;

    public static int exp;
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
                String val = snapshot.getValue().toString();
                exp = Integer.valueOf(val);

                int level = 0;
                int temp_exp = exp;

                for(; temp_exp > 0 ; temp_exp -= 25) temp_exp -= (level++*15);

                if(temp_exp == 0) ++level;
                TextView textView_level = (TextView) findViewById(R.id.level_view);

                textView_level.setText("Level " + level);

                TextView textview_exp = (TextView) findViewById(R.id.exp_view);
                int curr_exp_on_level = exp;
                --level;
                for(int i = 0 ; i < level; ++i) {
                    curr_exp_on_level -= 25;
                    curr_exp_on_level -= (i*15);
                }
                textview_exp.setText(curr_exp_on_level + " / " + (level*15 + 25));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        char_button = (ImageButton) findViewById(R.id.character_image);
        char_button.setOnTouchListener(new MyTouchListener());
        //char_button.setOnTouchListener(this);
    }
    private final class MyTouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent me){
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }
//
//    class MyDragListener implements View.OnDragListener {
//
//    }



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
