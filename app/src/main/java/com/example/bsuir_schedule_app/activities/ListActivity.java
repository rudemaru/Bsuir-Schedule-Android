package com.example.bsuir_schedule_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.bsuir_schedule_app.GroupAdapter;
import com.example.bsuir_schedule_app.R;
import com.example.bsuir_schedule_app.model.Schedule;
import com.example.bsuir_schedule_app.network.ApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonAdd, buttonRemove;
    private ListView listView;
    private ArrayList<String> itemList;
    private GroupAdapter adapter;
    private int selectedItemPosition = -1; // Хранит позицию выделенного элемента
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Инициализация FirebaseAuth
        auth = FirebaseAuth.getInstance();

        editText = findViewById(R.id.editText);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonRemove = findViewById(R.id.buttonRemove);
        listView = findViewById(R.id.listView);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Инициализация списка и адаптера
        itemList = new ArrayList<>();
        adapter = new GroupAdapter(this, itemList);
        listView.setAdapter(adapter);

        // Настройка Navigation Drawer
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Получаем текущего пользователя и обновляем заголовок
        FirebaseUser user = auth.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        TextView userEmail = headerView.findViewById(R.id.user_email);
        userEmail.setText(user != null ? user.getEmail() : "Не авторизован");

        // Обработчик кнопки выхода
        Button logoutButton = headerView.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(ListActivity.this, LoginActivity.class));
            finish(); // Закрыть текущую активность
        });

        // Обработчик нажатия на кнопку "Добавить"
        buttonAdd.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (!text.isEmpty()) {
                itemList.add(text);
                adapter.notifyDataSetChanged();
                editText.setText(""); // Очистка поля
            }
        });

        // Обработчик нажатия на кнопку "Удалить"
        buttonRemove.setOnClickListener(v -> {
            if (selectedItemPosition != -1) {
                itemList.remove(selectedItemPosition); // Удаление выделенного элемента
                adapter.notifyDataSetChanged();
                adapter.clearSelection(); // Сброс выделенной позиции в адаптере
                selectedItemPosition = -1; // Сброс выделенной позиции
            }
        });

        // Обработчик длительного нажатия для выделения элемента
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (selectedItemPosition == position) {
                adapter.clearSelection(); // Сброс выделенной позиции
                selectedItemPosition = -1; // Сброс выделенной позиции
            } else {
                adapter.setSelectedPosition(position); // Выделение элемента
                selectedItemPosition = position; // Устанавливаем выделенную позицию
            }
            return true; // Возвращаем true, чтобы обработать событие
        });

        // Обработчик двойного нажатия на элемент списка
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String groupNumber = itemList.get(position);
            fetchScheduleAndProceed(groupNumber); // Загружаем расписание
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers(); // Закрыть меню после выбора
            return true;
        });
    }

    private void fetchScheduleAndProceed(String groupNumber) {
        new Thread(() -> {
            try {
                ApiClient apiClient = new ApiClient();
                Schedule schedule = apiClient.getSchedule(groupNumber);

                runOnUiThread(() -> {
                    if (schedule != null && schedule.getSchedules() != null) {
                        Intent intent = new Intent(ListActivity.this, ScheduleActivity.class);
                        intent.putExtra("GROUP_NUMBER", groupNumber); // Передаем номер группы
                        startActivity(intent); // Переход на активность расписания
                    } else {
                        Toast.makeText(ListActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(ListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
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