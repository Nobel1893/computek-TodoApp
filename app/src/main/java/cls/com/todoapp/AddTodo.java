package cls.com.todoapp;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cls.com.todoapp.Base.MyBaseActivity;
import cls.com.todoapp.DataBase.Model.Todo;
import cls.com.todoapp.DataBase.TodoDatabase;

public class AddTodo extends MyBaseActivity implements View.OnClickListener {

    protected EditText title;
    protected EditText details;
    protected EditText time;
    protected Button insert;


    public static Todo todo;
    protected Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        if (todo != null) {
            title.setText(todo.getTitle());
            details.setText(todo.getDetails());
            time.setText(todo.getDateTime());
            insert.setText("update");
            delete.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.insert) {
            if (todo==null) {
                String sTitle = title.getText().toString();
                String sDetails = details.getText().toString();
                String sTime = time.getText().toString();
                Todo todo = new Todo(sTitle, sDetails, sTime);
                TodoDatabase.getIinstance(getApplicationContext()).todoDao().AddTodo(todo);
                finish();
            }else {
                String sTitle = title.getText().toString();
                String sDetails = details.getText().toString();
                String sTime = time.getText().toString();
                todo.setDateTime(sTime);
                todo.setDetails(sDetails);
                todo.setTitle(sTitle);
                TodoDatabase.getIinstance(getApplicationContext()).todoDao().Update(todo);
                todo=null;
                finish();
            }

        } else if (view.getId() == R.id.delete) {
            TodoDatabase.getIinstance(getApplicationContext()).todoDao().Delete(todo);
            todo=null;
            finish();

        }
    }

    private void initView() {
        title = (EditText) findViewById(R.id.title);
        details = (EditText) findViewById(R.id.details);
        time = (EditText) findViewById(R.id.time);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(AddTodo.this);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(AddTodo.this);
    }
}
