package com.bytedance.todolist.database;

import android.net.http.SslCertificate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
@Dao
public interface TodoListDao {
    @Query("SELECT * FROM todo")
    List<TodoListEntity> loadAll();

    @Insert
    long addTodo(TodoListEntity entity);

    @Query("DELETE FROM todo")
    void deleteAll();
    @Query("DELETE FROM todo WHERE id=:ID")
    void deleteEntry(Long ID);
}
