package com.example.messiah.questjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        super.onResume();

        arrayList = new ArrayList<ListElement>();
        myAdapter = new MyAdapter(this, R.layout.list_element , arrayList);
        ListView myListView = (ListView) findViewById(R.id.questListView);
        myListView.setAdapter(myAdapter);

        Firebase ref = new Firebase("https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests");
        // Attach a listener to read the data at our posts reference

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                arrayList.clear();
                System.out.println("There are " + snapshot.getChildrenCount() + " current quests");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    QuestObject quest = postSnapshot.getValue(QuestObject.class);

                    String difficulty = "noob";
                    switch (quest.getDifficulty()) {
                        case 1:
                            difficulty = "pleb";
                            break;
                        case 2:
                            difficulty = "veteran";
                            break;
                        default:
                            break;
                    }

                    arrayList.add(new ListElement(quest.getTitle(), quest.getDescription(), Integer.toString(quest.getDeadline()), difficulty, quest));
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i("mydebug", "The read failed: " + firebaseError.getMessage());
            }
        });
    }
}
