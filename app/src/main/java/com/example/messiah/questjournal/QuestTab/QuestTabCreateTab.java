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
    Calendar calendar;
    EditText dateInput;
    Button db;
    //DatePickerDialog.OnDateSetListener date;
    int year_x, month_x, day_x;
    //private DatePicker datePicker;
    static final int DIALOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab_create_tab);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        dateInput = (EditText) findViewById(R.id.deadline_input);
        showDialogOnClick();
    }

    public void showDialogOnClick() {
          dateInput.setOnClickListener(
                  new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          if (!isFinishing()) {
                              showDialog(DIALOG_ID);
                          }
                      }
                  }
          );
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this.getParent(), dpListener, year_x, month_x, day_x);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dpListener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            //Toast.makeText(QuestTabCreateTab.this, year_x + "/" + month_x +  "/" + day_x, Toast.LENGTH_SHORT).show();
            dateInput.setText((month_x) + "/" + day_x +  "/" + year_x);
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
        //int date = 0;
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

//        try {
//            date = Integer.parseInt(dateInput.getText().toString());
//        } catch (NumberFormatException e) {
//            Toast.makeText(getApplicationContext(), "Please enter a valid date.", Toast.LENGTH_SHORT).show();
//            return;
//        }

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
