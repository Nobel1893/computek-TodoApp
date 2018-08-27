package cls.com.todoapp.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import cls.com.todoapp.AddTodo;
import cls.com.todoapp.DataBase.Model.Todo;

/**
 * Created by CLS on 8/27/2018.
 */

@Dao
public interface TodoDao {

    @Query("select * from Todo;")
    List<Todo> SelectAllTodo();

    @Insert
    void AddTodo(Todo todo);
    //add any other functions
    @Delete
    void Delete(Todo todo);
    @Update
    void Update(Todo todo);

}
