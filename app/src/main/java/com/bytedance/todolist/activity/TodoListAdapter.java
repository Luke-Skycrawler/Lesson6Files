package com.bytedance.todolist.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListItemHolder> {
    private List<TodoListEntity> mDatas;
    private List<Long> ids=new ArrayList<>();
    private TodoListActivity parent;
    public TodoListAdapter(TodoListActivity parent) {
        mDatas = new ArrayList<>();
        this.parent=parent;
    }
    @NonNull
    @Override
    public TodoListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListItemHolder holder,final int position) {
        holder.bind(mDatas.get(position));
        final Long l=mDatas.get(position).getId();
        Log.i("LOG",""+l);
        ids.add(position,l);
//        Will it run in parallel?
//        final int pos=position;
        ImageButton deleteitem=holder.itemView.findViewById(R.id.X_for_delete);
        deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        final TodoListDao dao = TodoListDatabase.inst(parent).todoListDao();
                        dao.deleteEntry(l);
                        parent.loadFromDatabase();
                    }
                }.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @MainThread
    public void setData(List<TodoListEntity> list) {
        mDatas = list;

        notifyDataSetChanged();
    }
}
