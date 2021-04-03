package com.example.todoapp;
/*
Name: Suraj B.K
ID: C7205073
Developing Mobile Applications
* */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class EditTodoActivity extends AppCompatActivity {

    Fragment mnFragment;
    FragmentManager mnFragmentManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        mnFragment =new EditTodoFragment();
        mnFragmentManager=getSupportFragmentManager();
        mnFragmentManager.beginTransaction()
                .add(R.id.main_container, mnFragment)
                .commit();
    }
}