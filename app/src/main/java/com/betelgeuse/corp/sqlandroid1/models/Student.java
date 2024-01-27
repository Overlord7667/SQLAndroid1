package com.betelgeuse.corp.sqlandroid1.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;


public class Student implements Serializable {
    int age;
    String firstname;
    String name;
    int id;

    public Student(int age, String firstname, String name, int id) {
        this.age = age;
        this.firstname = firstname;
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return firstname + " | " + name + " | " + age;
    }

    Student(){}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(int age, String firstname, String name) {
        this.age = age;
        this.firstname = firstname;
        this.name = name;
    }
}
