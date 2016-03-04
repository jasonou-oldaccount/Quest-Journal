package com.example.messiah.questjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SettingTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_tab);
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
