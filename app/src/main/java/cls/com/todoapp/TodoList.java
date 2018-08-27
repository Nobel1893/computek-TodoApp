package cls.com.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import cls.com.todoapp.Adapters.TodoListAdapter;
import cls.com.todoapp.Base.MyBaseActivity;
import cls.com.todoapp.DataBase.Model.Todo;
import cls.com.todoapp.DataBase.TodoDatabase;

public class TodoList extends MyBaseActivity {

    RecyclerView todoRecyclerView;
    LinearLayoutManager layoutManager;
    TodoListAdapter adapter;
    List<Todo> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        todoRecyclerView = findViewById(R.id.todoList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(activity,AddTodo.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        items=TodoDatabase.getIinstance(getApplicationContext()).todoDao().SelectAllTodo();
        layoutManager=new LinearLayoutManager(activity);
        adapter=new TodoListAdapter(items);
        adapter.setOnItemClickListener(new TodoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Todo todo, int position) {
                AddTodo.todo= todo;
                startActivity(new Intent(activity,AddTodo.class));
            }
        });
        todoRecyclerView.setAdapter(adapter);
        todoRecyclerView.setLayoutManager(layoutManager);

    }
}
