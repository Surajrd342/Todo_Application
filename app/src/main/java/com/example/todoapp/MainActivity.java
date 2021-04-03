package com.example.todoapp;
/*
Name: Suraj B.K
ID: C7205073
Developing Mobile Applications
* */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;



import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.todoapp.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAddNew;

    Fragment mnfragment;
    FragmentManager mnfragmentManager;
    AlertDialog.Builder mnAlterDialog;
    TodoViewModel mnTodoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mnfragment =new TodoListFragment();
        mnfragmentManager =getSupportFragmentManager();
        mnfragmentManager.beginTransaction()
                .add(R.id.list_container, mnfragment)
                .commit();
        fabAddNew = findViewById(R.id.fab_add_new_todo);
        fabAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditTodoActivity.class);
                startActivity(intent);
            }
        });
        mnTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_delete_all:
                mnAlterDialog = new AlertDialog.Builder(this);
                mnAlterDialog.setMessage("Are you sure want to delete all??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setIcon(R.mipmap.ic_launcher);
                mnAlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mnTodoViewModel.deleteAll();
                    }
                });
                mnAlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mnAlterDialog.show();
            break;
            case R.id.menu_logout:
                mnAlterDialog = new AlertDialog.Builder(this);
                mnAlterDialog.setMessage("Are you sure want to logout??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setIcon(R.mipmap.ic_launcher);
                mnAlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                mnAlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mnAlterDialog.show();
                break;

            case R.id.delete_completed:
                mnAlterDialog = new AlertDialog.Builder(this);
                mnAlterDialog.setMessage("Are you sure want to delete completed task??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name))
                        .setIcon(R.mipmap.ic_launcher);
                mnAlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mnTodoViewModel.deleteCompleted();
                    }
                });
                mnAlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mnAlterDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
