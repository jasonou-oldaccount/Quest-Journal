package com.example.messiah.questjournal.QuestTab;

/**
 * Created by Raul on 3/5/2016.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messiah.questjournal.CharacterTabActivity;
import com.example.messiah.questjournal.MainActivity;
import com.example.messiah.questjournal.R;
import com.firebase.client.Firebase;

import java.util.List;

public class ViewQuestAdapter extends ArrayAdapter<ViewQuestListElement> {

    int resource;
    Context context;

    public ViewQuestAdapter(Context _context, int _resource, List<ViewQuestListElement> items) {
        super(_context, _resource, items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout newView;

        final ViewQuestListElement w = getItem(position);

        // Inflate a new view if necessary.
        if (convertView == null) {
            newView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource,  newView, true);
        } else {
            newView = (LinearLayout) convertView;
        }

        // Fills in the view.
        TextView titleView = (TextView) newView.findViewById(R.id.QuestTitle);
        TextView descView = (TextView) newView.findViewById(R.id.QuestDescription);
        TextView diffView = (TextView) newView.findViewById(R.id.QuestDifficulty);
        TextView deadView = (TextView) newView.findViewById(R.id.QuestDeadline);

        titleView.setText(w.title);
        descView.setText(w.description);
        diffView.setText(w.difficulty);
        deadView.setText(w.deadline);

        String refOldQuest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/OldQuests/";
        final Firebase OldQuest = new Firebase(refOldQuest);
        Button b = (Button) newView.findViewById(R.id.move_to_button);

        final String refCurrentQuest = "https://questjournal.firebaseio.com/users/" + MainActivity.UID + "/CurrentQuests/"+w.questObject.getQuestID();
        final Firebase curQuest = new Firebase(refCurrentQuest);

        // Sets a listener for the button, and a tag for the button as well.
        b.setTag(new Integer(position));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (w.questObject.getDifficulty()) {
                    case 0:
                        System.out.println("EASY ======" + w.questObject.getDifficulty());
                        CharacterTabActivity.curExp.setValue(CharacterTabActivity.exp + 5);
                    case 1:
                        System.out.println("MEDIUM ======" + w.questObject.getDifficulty());
                        CharacterTabActivity.curExp.setValue(CharacterTabActivity.exp + 10);
                        break;
                    case 2:
                        System.out.println("HARD ======" + w.questObject.getDifficulty());
                        CharacterTabActivity.curExp.setValue(CharacterTabActivity.exp + 20);
                        break;
                    default:
                        break;
                }

                String s = v.getTag().toString();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, s, duration);
                OldQuest.push().setValue(w.questObject);
                Log.i("debug", refCurrentQuest);
                curQuest.setValue(null);
                toast.show();
            }
        });
        return newView;
    }
}

