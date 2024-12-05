package com.example.bsuir_schedule_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class GroupAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> items;
    private int selectedPosition = -1; // Хранит позицию выделенного элемента

    public GroupAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.list_item, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = listItem.findViewById(R.id.item_text);
        textView.setText(items.get(position));

        // Изменение фона для выделенного элемента
        if (position == selectedPosition) {
            listItem.setBackgroundResource(R.drawable.selected_item_background); // Замените на свой фон
        } else {
            listItem.setBackgroundResource(R.drawable.default_item_background); // Замените на свой фон
        }

        return listItem;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    public void clearSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }
}