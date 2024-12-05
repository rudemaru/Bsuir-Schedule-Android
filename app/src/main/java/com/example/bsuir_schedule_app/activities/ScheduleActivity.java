package com.example.bsuir_schedule_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bsuir_schedule_app.R;
import com.example.bsuir_schedule_app.ScheduleAdapter;
import com.example.bsuir_schedule_app.model.LessonInfo;
import com.example.bsuir_schedule_app.model.Schedule;
import com.example.bsuir_schedule_app.network.ApiClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity {

    private static final String TAG = "ScheduleActivity"; // Тэг для логирования
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private List<Object> combinedList; // Список, который будет содержать заголовки и уроки
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private FirebaseAuth auth;
    private TextView weekNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        auth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        recyclerView = findViewById(R.id.recyclerView);
        combinedList = new ArrayList<>();

        scheduleAdapter = new ScheduleAdapter(combinedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scheduleAdapter);
        weekNumberTextView = findViewById(R.id.weekNumber);

        String groupNumber = getIntent().getStringExtra("GROUP_NUMBER");

        setupNavigationDrawer(navigationView);

        if (groupNumber != null) {
            new Thread(() -> {
                try {
                    ApiClient apiClient = new ApiClient();
                    Schedule schedule = apiClient.getSchedule(groupNumber);
                    Integer currentWeek = apiClient.getCurrentWeek();

                    runOnUiThread(() -> {
                        if (schedule != null && schedule.getSchedules() != null) {

                            Log.i(TAG, "schedule != null && schedule.getSchedules() != null");
                            weekNumberTextView.setText(String.valueOf(currentWeek));

                            List<LessonInfo> mondayLessons = new ArrayList<>();
                            List<LessonInfo> tuesdayLessons = new ArrayList<>();
                            List<LessonInfo> wednesdayLessons = new ArrayList<>();
                            List<LessonInfo> thursdayLessons = new ArrayList<>();
                            List<LessonInfo> fridayLessons = new ArrayList<>();
                            List<LessonInfo> saturdayLessons = new ArrayList<>();

                            for (LessonInfo lessonInfo : schedule.getSchedules().getMonday()) {

                                Log.i(TAG, "MONDAY\n" + LessonInfo.class.toString());

                                if (!lessonInfo.getAnnouncement() &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("консультация") &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("экзамен") && lessonInfo.getWeekNumber().contains(currentWeek)){
                                    mondayLessons.add(lessonInfo);
                                }
                            }

                            for (LessonInfo lessonInfo : schedule.getSchedules().getTuesday()) {

                                Log.i(TAG, "TUESDAY\n" + LessonInfo.class.toString());

                                if (!lessonInfo.getAnnouncement() &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("консультация") &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("экзамен") && lessonInfo.getWeekNumber().contains(currentWeek)) {
                                    tuesdayLessons.add(lessonInfo);
                                }
                            }

                            for (LessonInfo lessonInfo : schedule.getSchedules().getWednesday()) {

                                Log.i(TAG, "WEDNESDAY\n" + LessonInfo.class.toString());

                                if (!lessonInfo.getAnnouncement() &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("консультация") &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("экзамен") && lessonInfo.getWeekNumber().contains(currentWeek)){
                                    wednesdayLessons.add(lessonInfo);
                                }
                            }

                            for (LessonInfo lessonInfo : schedule.getSchedules().getThursday()) {

                                Log.i(TAG, "THURSDAY\n" + LessonInfo.class.toString());

                                if (!lessonInfo.getAnnouncement() &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("консультация") &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("экзамен") && lessonInfo.getWeekNumber().contains(currentWeek)){
                                    thursdayLessons.add(lessonInfo);
                                }
                            }

                            for (LessonInfo lessonInfo : schedule.getSchedules().getFriday()) {

                                Log.i(TAG, "FRIDAY\n" + LessonInfo.class.toString());

                                if (!lessonInfo.getAnnouncement() &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("консультация") &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("экзамен") && lessonInfo.getWeekNumber().contains(currentWeek)){
                                    fridayLessons.add(lessonInfo);
                                }
                            }

                            for (LessonInfo lessonInfo : schedule.getSchedules().getSaturday()) {

                                Log.i(TAG, "SATURDAY\n" + LessonInfo.class.toString());

                                if (!lessonInfo.getAnnouncement() &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("консультация") &&
                                        !lessonInfo.getLessonTypeAbbrev().equalsIgnoreCase("экзамен") && lessonInfo.getWeekNumber().contains(currentWeek)){
                                    saturdayLessons.add(lessonInfo);
                                }
                            }

                            addDayHeaders(combinedList, mondayLessons, tuesdayLessons, wednesdayLessons,
                                    thursdayLessons, fridayLessons, saturdayLessons);

                            scheduleAdapter.notifyDataSetChanged();
                            Log.i(TAG, "RASPISANIE OBRABOTANO");
                            Toast.makeText(ScheduleActivity.this, "Расписание получено", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ScheduleActivity.this, "Данных нету", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(ScheduleActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                }
            }).start();
        } else {
            Toast.makeText(this, "Номер группы не указан", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupNavigationDrawer(NavigationView navigationView) {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Получаем текущего пользователя и обновляем заголовок
        FirebaseUser user = auth.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        TextView userEmail = headerView.findViewById(R.id.user_email);
        userEmail.setText(user != null ? user.getEmail() : "Не авторизован");

        Button logoutButton = headerView.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(ScheduleActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void addDayHeaders(List<Object> combinedList, List<LessonInfo> mondayLessons,
                               List<LessonInfo> tuesdayLessons, List<LessonInfo> wednesdayLessons,
                               List<LessonInfo> thursdayLessons, List<LessonInfo> fridayLessons,
                               List<LessonInfo> saturdayLessons) {
        combinedList.add("Понедельник");
        combinedList.addAll(mondayLessons);
        combinedList.add("Вторник");
        combinedList.addAll(tuesdayLessons);
        combinedList.add("Среда");
        combinedList.addAll(wednesdayLessons);
        combinedList.add("Четверг");
        combinedList.addAll(thursdayLessons);
        combinedList.add("Пятница");
        combinedList.addAll(fridayLessons);
        combinedList.add("Суббота");
        combinedList.addAll(saturdayLessons);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}