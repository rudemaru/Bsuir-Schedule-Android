package com.example.bsuir_schedule_app; // Замените на ваш пакет

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    private int selectedPosition = -1; // Хранит позицию выделенного элемента

    public GroupAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.textViewItem);
        textView.setText(values.get(position));

        // Изменение цвета фона для выделенного элемента
        if (position == selectedPosition) {
            rowView.setBackgroundColor(Color.LTGRAY); // Цвет для выделенного элемента
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT); // Цвет для невыделенных элементов
        }

        return rowView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Обновление списка
    }

    public void clearSelection() {
        selectedPosition = -1; // Сброс выделенной позиции
        notifyDataSetChanged(); // Обновление списка
    }
}