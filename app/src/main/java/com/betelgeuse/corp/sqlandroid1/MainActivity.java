package com.betelgeuse.corp.sqlandroid1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.betelgeuse.corp.sqlandroid1.adapter.StudentListAdapter;
import com.betelgeuse.corp.sqlandroid1.models.Student;
import com.betelgeuse.corp.sqlandroid1.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add_btn;
    List<Student> students = new ArrayList<>();
    ListView listView;
    StudentListAdapter studentListAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_btn = findViewById(R.id.addNewPerson);
        listView = findViewById(R.id.ContactListID);

        dbHelper = new DBHelper(this);
//        dbHelper.addStudent(new Student(26, "Petrovich", "Vasya"));

        List<Student> students = dbHelper.listStudent();
        for (int i = 0; i <students.size(); i++) {

        }

        // Инициализация списка студентов и адаптера
        studentListAdapter = new StudentListAdapter(this, students);
        listView.setAdapter(studentListAdapter);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TakerPerson.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            // Если результат успешный, обновляем список студентов
            students.clear();
            students.addAll(dbHelper.listStudent());
            studentListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Студент успешно добавлен", Toast.LENGTH_SHORT).show();
        }
    }

    // Метод для обновления списка студентов после изменений
    private void updateStudentList() {
        students.clear(); // Очищаем текущий список студентов
        students.addAll(dbHelper.listStudent()); // Получаем новый список студентов из базы данных
        studentListAdapter.notifyDataSetChanged(); // Уведомляем адаптер об изменениях
    }
}