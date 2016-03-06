package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class QuestTabCreateTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab_create_tab);
    }

    public void createQuest(View view) {
        String ref_quest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests/";
        Firebase newQuest = new Firebase(ref_quest);

        EditText title = (EditText) findViewById(R.id.title_input);
        EditText desc = (EditText) findViewById(R.id.description_input);
        EditText dateInput = (EditText) findViewById(R.id.deadline_input);

        if (title.getText().toString().equals ("") | desc.getText().toString().equals("") | dateInput.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        int difficulty = 0;
        int date = 0;
        RadioGroup difficultySelection = (RadioGroup) findViewById(R.id.difficulty_i);
        int selectedID = difficultySelection.getCheckedRadioButtonId();

        RadioButton radioSelection = (RadioButton) findViewById(selectedID);
        switch (radioSelection.getText().toString()){
            case "pleb":
                difficulty = 1;
                break;
            case "veteran":
                difficulty = 2;
                break;
            default:
                break;
        }

        try {
            date = Integer.parseInt(dateInput.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid date.", Toast.LENGTH_SHORT).show();
        }

        QuestObject createQuest = new QuestObject(title.getText().toString(), difficulty, desc.getText().toString(), date);
        newQuest.push().setValue(createQuest);
        Toast.makeText(getApplicationContext(), "Quest created!", Toast.LENGTH_SHORT).show();

        title.setText("");
        desc.setText("");
        dateInput.setText("");
    }
}
