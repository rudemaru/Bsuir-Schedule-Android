package com.example.bsuir_schedule_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bsuir_schedule_app.model.LessonInfo;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_DAY_HEADER = 0;
    private static final int VIEW_TYPE_LESSON = 1;

    private List<Object> combinedList; // Список, который включает заголовки и уроки

    // Конструктор
    public ScheduleAdapter(List<Object> combinedList) {
        this.combinedList = combinedList;
    }

    @Override
    public int getItemViewType(int position) {
        if (combinedList.get(position) instanceof String) {
            return VIEW_TYPE_DAY_HEADER; // Заголовок дня
        } else {
            return VIEW_TYPE_LESSON; // Урок
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DAY_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_header, parent, false);
            return new DayViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
            return new LessonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DayViewHolder) {
            String day = (String) combinedList.get(position);
            ((DayViewHolder) holder).dayTextView.setText(day);
        } else if (holder instanceof LessonViewHolder) {
            LessonInfo lesson = (LessonInfo) combinedList.get(position);
            ((LessonViewHolder) holder).subjectTextView.setText(lesson.getSubject());
            ((LessonViewHolder) holder).timeTextView.setText(lesson.getStartLessonTime() + " - " + lesson.getEndLessonTime());
            ((LessonViewHolder) holder).lessonTypeTextView.setText(lesson.getLessonTypeAbbrev());

            // Отображение подгруппы
            if (lesson.getNumSubgroup() != null && lesson.getNumSubgroup() > 0) {
                ((LessonViewHolder) holder).subgroupTextView.setVisibility(View.VISIBLE);
                ((LessonViewHolder) holder).subgroupTextView.setText("Подгруппа: " + lesson.getNumSubgroup());
            } else {
                ((LessonViewHolder) holder).subgroupTextView.setVisibility(View.GONE);
            }

            // Отображение номеров недель
            StringBuilder weekNumbers = new StringBuilder("Недели: ");
            List<Integer> weekNumbersList = lesson.getWeekNumber();
            if (weekNumbersList != null && !weekNumbersList.isEmpty()) {
                for (Integer weekNumber : weekNumbersList) {
                    weekNumbers.append(weekNumber).append(" ");
                }
            } else {
                weekNumbers.append("Нет данных");
            }
            ((LessonViewHolder) holder).weekNumberTextView.setText(weekNumbers.toString().trim());
        }
    }

    @Override
    public int getItemCount() {
        return combinedList != null ? combinedList.size() : 0; // Проверяем на null
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
        }
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView subjectTextView;
        TextView timeTextView;
        TextView subgroupTextView;
        TextView lessonTypeTextView;
        TextView weekNumberTextView; // Поле для отображения номеров недель

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            subgroupTextView = itemView.findViewById(R.id.subgroupTextView);
            lessonTypeTextView = itemView.findViewById(R.id.lessonTypeTextView);
            weekNumberTextView = itemView.findViewById(R.id.weekNumberTextView); // Инициализация
        }
    }
}