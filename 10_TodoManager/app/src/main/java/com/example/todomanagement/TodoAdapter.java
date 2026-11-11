package com.example.todomanagement;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Custom ArrayAdapter that shows done items with strikethrough text.
 */
public class TodoAdapter extends ArrayAdapter<TodoItem> {

    public TodoAdapter(Context context, List<TodoItem> items) {
        super(context, android.R.layout.simple_list_item_1, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView tv = view.findViewById(android.R.id.text1);
        TodoItem item = getItem(position);
        if (item != null) {
            tv.setText(item.getText());
            if (item.isDone()) {
                tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tv.setAlpha(0.5f);
            } else {
                tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                tv.setAlpha(1.0f);
            }
        }
        return view;
    }
}
