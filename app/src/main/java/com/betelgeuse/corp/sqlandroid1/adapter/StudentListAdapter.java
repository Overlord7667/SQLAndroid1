package com.betelgeuse.corp.sqlandroid1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.betelgeuse.corp.sqlandroid1.MainActivity;
import com.betelgeuse.corp.sqlandroid1.R;
import com.betelgeuse.corp.sqlandroid1.models.Student;

import java.util.List;

public class StudentListAdapter extends BaseAdapter {
    private Context context;
    private List<Student> students;

    public StudentListAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Этот метод вызывается, когда данные в адаптере изменяются


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.card_student, parent, false);
        }

        // Получаем данные студента для текущей позиции
        Student student = students.get(position);

        // Находим TextView в макете элемента списка
        TextView nameTextView = view.findViewById(R.id.NameText);
        TextView secondTextView = view.findViewById(R.id.SecondNameText);
        TextView ageTextView = view.findViewById(R.id.YearText);

        // Заполняем TextView данными студента
        nameTextView.setText(student.getFirstname());
        secondTextView.setText(student.getName());
        ageTextView.setText(String.valueOf(student.getAge()));

        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void updateData(List<Student> newStudents) {
        students.clear();
        students.addAll(newStudents);
        notifyDataSetChanged();
    }
}
