package com.example.messiah.questjournal.QuestTab;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.messiah.questjournal.R;

import java.util.List;

/**
 * Created by Messiah on 3/9/2016.
 */
public class OldQuestAdapter extends ArrayAdapter<ViewQuestListElement> {

    int resource;
    Context context;

    public OldQuestAdapter(Context _context, int _resource, List<ViewQuestListElement> items) {
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

        titleView.setTypeface(QuestTabActivity.type);
        descView.setTypeface(QuestTabActivity.type);
        diffView.setTypeface(QuestTabActivity.type);
        deadView.setTypeface(QuestTabActivity.type);

        titleView.setPaintFlags(titleView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        titleView.setText(w.title);
        descView.setText(w.description);
        diffView.setText("Difficulty: " + w.difficulty);
        deadView.setText("Deadline: " + w.deadline);

        return newView;
    }
}