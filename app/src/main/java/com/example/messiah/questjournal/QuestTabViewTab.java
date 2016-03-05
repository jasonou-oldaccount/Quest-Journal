package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class QuestTabViewTab extends AppCompatActivity {


    private MyAdapter myAdapter;

    private ArrayList<ListElement> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_tab_view_tab);



    }

    @Override
    protected void onResume() {
        arrayList = new ArrayList<ListElement>();
        myAdapter = new MyAdapter(this, R.layout.list_element , arrayList);
        ListView myListView = (ListView) findViewById(R.id.questListView);
        myListView.setAdapter(myAdapter);
        getQuests();
        myAdapter.notifyDataSetChanged();
        super.onResume();
    }



    public void refreshQuests(View view){
        getQuests();
    }

    public void getQuests(){
        Firebase ref = new Firebase("https://questjournal.firebaseio.com/users/931b4d56-bf3e-4e12-a53b-fe6d52847324/CurrentQuests");

        // Attach an listener to read the data at our posts reference

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " current quests");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    QuestObject quest = postSnapshot.getValue(QuestObject.class);
                    arrayList.add(new ListElement(quest.getTitle(), quest.getDescription(), Integer.toString(quest.getDeadline()), Integer.toString(quest.getDifficulty())));
                    System.out.println(quest.getTitle() + " --- " + quest.getDescription());
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }
}
