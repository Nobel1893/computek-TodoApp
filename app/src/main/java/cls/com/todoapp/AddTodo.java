package cls.com.todoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;

import cls.com.todoapp.Base.MyBaseActivity;
import cls.com.todoapp.DataBase.Model.Todo;
import cls.com.todoapp.DataBase.TodoDatabase;
import cls.com.todoapp.Recievers.AlarmReciever;

public class AddTodo extends MyBaseActivity implements View.OnClickListener ,
        TimePickerDialog.OnTimeSetListener{

    protected EditText title;
    protected EditText details;
    protected TextView time;
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
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        AddTodo.this ,
                        now.get(Calendar.HOUR_OF_DAY), // Initial year selection
                        now.get(Calendar.MINUTE), // Initial month selection
                        false); // Inital day selection;
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        if (todo != null) {
            title.setText(todo.getTitle());
            details.setText(todo.getDetails());
            time.setText(todo.getDateTime());
            insert.setText("update");
            delete.setVisibility(View.VISIBLE);

        }

    }

    int hourOfDay;
    int minutes;
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        this.hourOfDay=hourOfDay;
        this.minutes=minute;
        time.setText(hourOfDay+ ":"+minute);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.insert) {
            if (todo==null) {
                String sTitle = title.getText().toString();
                String sDetails = details.getText().toString();
                String sTime = hourOfDay+":"+minutes;
                Todo todo = new Todo(sTitle, sDetails, sTime);
                TodoDatabase.getIinstance(getApplicationContext()).todoDao().AddTodo(todo);
                SetAlarm();
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
        time =  findViewById(R.id.time);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(AddTodo.this);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(AddTodo.this);
    }

   public void SetAlarm(){
        int seconds=getTimeInterval();
        Log.e("alarm set for ",seconds+ " seconds");
        triggerAlarmManager(seconds);

        }
    //get time interval to trigger alarm manager
    private int getTimeInterval() {

        Calendar calendar = Calendar.getInstance();

        int curr_hr=calendar.get(Calendar.HOUR_OF_DAY);
        int curr_min=calendar.get(Calendar.MINUTE);

        Log.e("current",curr_hr+" "+curr_min+"");
        Log.e("target",hourOfDay+" " +minutes+"");

        return ((hourOfDay-curr_hr)*60*60)+((minutes-curr_min)*60);
    }


    //Trigger alarm manager with entered time interval
    public void triggerAlarmManager(int alarmTriggerTime) {
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add alarmTriggerTime seconds to the calendar object
       // cal.add(Calendar.SECOND, alarmTriggerTime);
        cal.add(Calendar.SECOND, alarmTriggerTime);
        Intent i=new Intent(this,AlarmReciever.class);
        i.putExtra("name",title.getText().toString());
        i.putExtra("desc",details.getText().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 132, i, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);//set alarm manager with entered timer by converting into milliseconds
        Toast.makeText(this, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();
    }
}
