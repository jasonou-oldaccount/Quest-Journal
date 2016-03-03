package com.example.messiah.questjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Firebase ref used to reference our Firebase backend
    public static Firebase ref;
    int createUserClick = 0;

    // on create establish firebase connection
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the Android Context for Firebase
        Firebase.setAndroidContext(this);

        // Creates a new Firebase ref using specified path
        ref = new Firebase("https://questjournal.firebaseio.com/");
    }

    // sign in
    public void signIn(View v) {
        TextView user = (TextView) findViewById(R.id.username);
        TextView status = (TextView) findViewById(R.id.password);
        final TextView nickname = (TextView) findViewById(R.id.character);

        ref.authWithPassword(user.getText().toString(), status.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                Toast.makeText(getApplicationContext(), "Logged in successfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("com.example.messiah.questjournal.CharacterActivity"));
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                Toast.makeText(getApplicationContext(), "Failed to log in, please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //create account
    public void create(final View v){

        TextView user = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        final TextView character = (TextView) findViewById(R.id.character);
        TextView pass2 = (TextView) findViewById(R.id.password2);
        ImageButton logIn = (ImageButton) findViewById(R.id.login_button);
        ImageButton create = (ImageButton) findViewById(R.id.create_button);

        if(createUserClick == 0){
            Log.i("create", "entered if");
            logIn.setVisibility(View.INVISIBLE);
            create.animate().translationYBy(150f).setDuration(1000);
            character.setVisibility(View.VISIBLE);
            pass2.setVisibility(View.VISIBLE);
            createUserClick = 1;
            return;
        }
        if (createUserClick == 1) {
            if (user.getText().toString().equals("") | password.getText().toString().equals("") | character.getText().toString().equals("") | pass2.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            } else if(!password.getText().toString().equals(pass2.getText().toString())){
                Toast.makeText(getApplicationContext(), "Passwords did not match.", Toast.LENGTH_SHORT).show();
            } else {
                ref.createUser(user.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Log.i("auth", "Successfully created user account with uid: " + result.get("uid"));
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("nickname", character.getText().toString());
                        ref.child("users").child(result.get("uid").toString()).setValue(map);

                        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        signIn(v);
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                        Log.i("auth", "error creating user");
                        Toast.makeText(getApplicationContext(), "Failed to create account, one or more fields is not valid.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
