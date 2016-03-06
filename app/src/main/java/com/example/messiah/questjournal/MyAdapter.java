package com.example.messiah.questjournal;

/**
 * Created by Raul on 3/5/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<ListElement> {

    int resource;
    Context context;

    public MyAdapter(Context _context, int _resource, List<ListElement> items) {
        super(_context, _resource, items);
        resource = _resource;
        context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout newView;

        ListElement w = getItem(position);

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
        return newView;
    }
}

