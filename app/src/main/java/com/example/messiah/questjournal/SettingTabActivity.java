package com.example.messiah.questjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SettingTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_tab);

        String nickname_ref = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/nickname";

        Firebase ref = new Firebase(nickname_ref);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                EditText textview_nickname = (EditText) findViewById(R.id.settings_nickname);
                textview_nickname.setText("");
                textview_nickname.setHint("Change nickname: " + snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }

    public void updateNickname(View view){
        EditText newName = (EditText) findViewById(R.id.settings_nickname);
        String nicknameref = "https://questjournal.firebaseio.com/users/" + MainActivity.UID;
        Log.i("auth", nicknameref);
        Firebase ref = new Firebase(nicknameref);
//        Firebase nicknameRef = ref;
        Map<String, Object> nickname = new HashMap<String, Object>();
        nickname.put("nickname", newName.getText().toString());
        ref.updateChildren(nickname);
    }
    public void logOut(View view){
        MainActivity.ref.unauth();
        Toast.makeText(getApplicationContext(), "logged out", Toast.LENGTH_SHORT).show();
        Log.i("auth", "logged out");
        Intent newIntent = new Intent(SettingTabActivity.this, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent);
    }
}