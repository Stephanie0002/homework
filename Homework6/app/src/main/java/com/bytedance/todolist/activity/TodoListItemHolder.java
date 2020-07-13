package com.bytedance.todolist.activity;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListItemHolder extends RecyclerView.ViewHolder {
    private TextView mContent;
    private TextView mTimestamp;
    private CheckBox cb;
    private Button btn_x;
    private TodoListAdapter mAdapter;

    public TodoListItemHolder(@NonNull View itemView) {
        super(itemView);
        mContent = itemView.findViewById(R.id.tv_content);
        mTimestamp = itemView.findViewById(R.id.tv_timestamp);
        cb = itemView.findViewById(R.id.cb);
        btn_x = itemView.findViewById(R.id.btn_x);
        mAdapter = new TodoListAdapter();
    }

    public void bind(final TodoListEntity entity) {
        mContent.setText(entity.getContent());
        mTimestamp.setText(formatDate(entity.getTime()));
        cb.setChecked(entity.getCheck());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb.isChecked())
                {
                    mContent.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
                    mContent.setTextColor(Color.parseColor("#B2B2B2"));
                    entity.setCheck(true);
                    new Thread() {
                        @Override
                        public void run() {
                            TodoListDao dao = TodoListDatabase.inst(itemView.getContext()).todoListDao();
                            dao.updataTodo(entity);
                        }
                    }.start();
                }
                else
                {
                    mContent.getPaint().setFlags(0);  // 取消设置的的划线
                    mContent.setTextColor(Color.parseColor("#000000"));
                    entity.setCheck(false);
                    new Thread() {
                        @Override
                        public void run() {
                            TodoListDao dao = TodoListDatabase.inst(itemView.getContext()).todoListDao();
                            dao.updataTodo(entity);
                        }
                    }.start();
                }
            }
        });

        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(view.getContext()).todoListDao();
                        dao.deleteTodo(entity);
                    }
                }.start();
                cb.setChecked(true);
                mContent.setText("");
                mTimestamp.setText("");
                Toast.makeText(view.getContext(), "删除成功，重启应用或其余刷新时刷新", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String formatDate(Date date) {
        DateFormat format = SimpleDateFormat.getDateInstance();
        return format.format(date);
    }
}


