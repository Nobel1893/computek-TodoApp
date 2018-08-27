package cls.com.todoapp.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import cls.com.todoapp.DataBase.Model.Todo;

/**
 * Created by CLS on 8/27/2018.
 */



@Database(entities = {Todo.class}, version = 1 ,exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase INSTANCE;

    public abstract TodoDao todoDao();

    public static TodoDatabase getIinstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            TodoDatabase.class, "Todo-DB")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }




}
