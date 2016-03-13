package com.example.messiah.questjournal.QuestTab;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.messiah.questjournal.MainActivity;
import com.example.messiah.questjournal.R;
import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class QuestTabCreateTab extends AppCompatActivity {
    EditText dateInput;
<<<<<<< HEAD
    int year_dl, month_dl, day_dl;
=======

    //DatePickerDialog.OnDateSetListener date;
    int year_x, month_x, day_x;
    //private DatePicker datePicker;
>>>>>>> ce0a29108bb1f68b3135a424d96dc6b06ace6247
    static final int DIALOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab_create_tab);

        // Getting current date as reference for user's deadline entry
        final Calendar cal = Calendar.getInstance();
        year_dl = cal.get(Calendar.YEAR);
        month_dl = cal.get(Calendar.MONTH);
        day_dl = cal.get(Calendar.DAY_OF_MONTH);
        dateInput = (EditText) findViewById(R.id.deadline_input);

        showDialogOnClick();
    }
    // Shows calendar when deadline EditText box is clicked
    public void showDialogOnClick() {
          dateInput.setOnClickListener(
                  new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          String val = (isFinishing()) ? "isFinishing" : "finished";
                          Log.i("debug", val);
                          if (!isFinishing()) {
                              showDialog(DIALOG_ID);
                          }
                      }
                  }
          );
    }

<<<<<<< HEAD
=======

>>>>>>> ce0a29108bb1f68b3135a424d96dc6b06ace6247
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this.getParent(), dpListener, year_dl, month_dl, day_dl);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dpListener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_dl = year;
            month_dl = monthOfYear + 1;
            day_dl = dayOfMonth;
            dateInput.setText((month_dl) + "/" + day_dl +  "/" + year_dl);
        }
    };

    public void createQuest(View view) {
        String ref_quest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests/";
        Firebase newQuest = new Firebase(ref_quest);

        EditText title = (EditText) findViewById(R.id.title_input);
        EditText desc = (EditText) findViewById(R.id.description_input);


        if (title.getText().toString().equals ("") | desc.getText().toString().equals("") | dateInput.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        int difficulty = 0;
        RadioGroup difficultySelection = (RadioGroup) findViewById(R.id.difficulty_i);
        int selectedID = difficultySelection.getCheckedRadioButtonId();

        RadioButton radioSelection = (RadioButton) findViewById(selectedID);
        switch (radioSelection.getText().toString()){
            case "Normal":
                difficulty = 1;
                break;
            case "Advanced":
                difficulty = 2;
                break;
            default:
                break;
        }

        //Setting up a new quest object that includes title, description, difficulty, deadline, and id
        //the id is used to move the quest from current list to completed list
        Firebase newFireQuest= newQuest.push();
        String questID = newFireQuest.getKey();
        QuestObject createQuest = new QuestObject(title.getText().toString(), difficulty, desc.getText().toString(), dateInput.getText().toString(), questID);
        newFireQuest.setValue(createQuest);

        Log.i("debug", questID);
        Toast.makeText(getApplicationContext(), "Quest created!", Toast.LENGTH_SHORT).show();

        title.setText("");
        desc.setText("");
        dateInput.setText("");
    }
}
