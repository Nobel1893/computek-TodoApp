package cls.com.todoapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cls.com.todoapp.DataBase.Model.Todo;
import cls.com.todoapp.R;


/**
 * Created by CLS on 8/1/2018.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder>{


    List<Todo> items;
    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TodoListAdapter(List<Todo> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item ,null);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Todo todo = items.get(position);
        holder.title.setText(todo.getTitle());
        holder.details.setText(todo.getDetails());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(items.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView details;
        View parent;
        public ViewHolder(View cardView){
            super(cardView);
            title=cardView.findViewById(R.id.title);
             details=cardView.findViewById(R.id.details);
             parent=cardView.findViewById(R.id.parent);

        }



    }

    public static interface OnItemClickListener{
       void onItemClick(Todo todo, int position);
    }

}
