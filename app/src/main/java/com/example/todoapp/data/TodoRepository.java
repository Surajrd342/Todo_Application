package com.example.todoapp.data;
/*
Name: Suraj B.K
ID: C7205073
Developing Mobile Applications
* */
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoapp.model.ETodo;

import java.util.List;

public class TodoRepository {
    private TodoDAO mnTodoDAO;
    private LiveData<List<ETodo>> mnAllTodoList;

    public TodoRepository(Application application) {
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mnTodoDAO =database.mTodoDAO();
        mnAllTodoList = mnTodoDAO.getAllTodos();

    }

    public LiveData<List<ETodo>> getMnAllTodoList() {
        return mnAllTodoList;
    }

    public ETodo getTodoById(int id){
        return mnTodoDAO.getTodoById(id);
    }

    public void insert(ETodo todo){
        new insertTodoAsynchTask(mnTodoDAO).execute(todo);
    }

    public void deleteAll(){
        new deleteAllTodoAsynchTask(mnTodoDAO).execute();
    }
    public void update(ETodo todo){
        new updateTodoAsynchTask(mnTodoDAO).execute(todo);
    }

    public void deleteById(ETodo todo){
        new deleteByIdTodoAsynchTask(mnTodoDAO).execute(todo);
    }
    public void deleteCompleted(){
        new deleteCompletedTodoAsynchTask(mnTodoDAO).execute();
    }

    private static class deleteCompletedTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO mTodoDAO;
        private deleteCompletedTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {

            mTodoDAO.deleteCompleted();
            return null;
        }
    }

    private static class insertTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO mTodoDAO;
        private insertTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
           mTodoDAO.insert(todos[0]);
            return null;
        }
    }
    private static class updateTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO mTodoDAO;
        private updateTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
            mTodoDAO.update(todos[0]);
            return null;
        }
    }
    private static class deleteAllTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO mTodoDAO;
        private deleteAllTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {

            mTodoDAO.deleteAll();
            return null;
        }
    }
    private static class deleteByIdTodoAsynchTask extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO mTodoDAO;
        private deleteByIdTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
            mTodoDAO.deleteById(todos[0]);
            return null;
        }
    }
}
