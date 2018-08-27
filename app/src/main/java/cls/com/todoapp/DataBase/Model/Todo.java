package cls.com.todoapp.DataBase.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by CLS on 8/27/2018.
 */

@Entity
public class Todo {


    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String title;
    @ColumnInfo
    String details;
    @ColumnInfo
    String dateTime;

    public Todo(String title, String details, String dateTime) {
        this.title = title;
        this.details = details;
        this.dateTime = dateTime;
    }

    public Todo(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
