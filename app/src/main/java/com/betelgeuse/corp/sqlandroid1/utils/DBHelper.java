package com.betelgeuse.corp.sqlandroid1.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.betelgeuse.corp.sqlandroid1.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentDB";
    private static final String TABLE_STUDENTS = "Students";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_FIRSTNAME= "Firstname";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_ID = "Id";
    private static final String CREATE_STUDENTS_TABLE_SCRIPT =
            "CREATE TABLE IF NOT EXISTS Students (" +
            "    Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "    Name TEXT NOT NULL, " +
            "    Firstname TEXT NOT NULL, " +
            "    Age INTEGER NOT NULL)";

    public DBHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_STUDENTS_TABLE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL();
    }

    public void addStudent(Student student){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, student.getName());
            values.put(COLUMN_FIRSTNAME, student.getFirstname());
            values.put(COLUMN_AGE, student.getAge());
            db.insert(TABLE_STUDENTS, null ,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int updateStudent(Student student){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, student.getName());
            values.put(COLUMN_FIRSTNAME, student.getFirstname());
            values.put(COLUMN_AGE, student.getAge());
            int result = db.update(TABLE_STUDENTS, values, COLUMN_ID + " = ? ",new String[]{ String.valueOf(student.getId())});
            db.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public int deleteStudent(Student student){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            int result = db.delete(TABLE_STUDENTS, COLUMN_ID + " = ? ",
                    new String[]{ String.valueOf(student.getId())});
            db.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public Student readStudent(int Id){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor =  db.query(TABLE_STUDENTS,
                    new String[]{COLUMN_ID, COLUMN_AGE, COLUMN_FIRSTNAME, COLUMN_NAME},
                    COLUMN_ID + " = ? ",new String[]{String.valueOf(Id)},
                    null,null,null,null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            Student student = new Student(cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(0));
            db.close();
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Student> listStudent(){
        List<Student> listStudent = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor =  db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
            if (cursor != null && cursor.moveToFirst()){
//                cursor.moveToFirst();
                do {
                    Student student = new Student(cursor.getInt(3),
                            cursor.getString(2),
                            cursor.getString(1),
                            cursor.getInt(0));
                    listStudent.add(student);
                }while (cursor.moveToNext());
                cursor.close();
            }
            db.close();
            return listStudent;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
