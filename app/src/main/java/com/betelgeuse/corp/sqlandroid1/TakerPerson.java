package com.betelgeuse.corp.sqlandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.betelgeuse.corp.sqlandroid1.models.Student;
import com.betelgeuse.corp.sqlandroid1.utils.DBHelper;

public class TakerPerson extends AppCompatActivity {

    Button addBtn;
    EditText firstNameText, secondNameText, yearOldText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taker_person);

        dbHelper = new DBHelper(this);

        addBtn = findViewById(R.id.addPersonBTN);
        firstNameText = findViewById(R.id.nameText);
        secondNameText = findViewById(R.id.secondName);
        yearOldText = findViewById(R.id.yearText);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем данные из полей ввода
                String firstName = firstNameText.getText().toString();
                String secondName = secondNameText.getText().toString();
                int age = Integer.parseInt(yearOldText.getText().toString());

                // Создаем объект Student
                Student student = new Student(age, firstName, secondName);

                // Вызываем метод addStudent в DBHelper для добавления студента в базу данных
                dbHelper.addStudent(student);

                // Очищаем поля ввода после добавления студента
                firstNameText.setText("");
                secondNameText.setText("");
                yearOldText.setText("");

                Intent intent = new Intent(TakerPerson.this, MainActivity.class);
                startActivity(intent);
                // Выводим сообщение об успешном добавлении студента
                Toast.makeText(TakerPerson.this, "Студент успешно добавлен", Toast.LENGTH_SHORT).show();
            }
        });

    }
}