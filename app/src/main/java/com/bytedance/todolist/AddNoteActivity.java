package com.bytedance.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity {
    private EditText TODOtext;
    protected String todo;
    private final String KEY_1="key_1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_layout);
        Log.i("RecycleView", "RecycleView Clicked");
        TODOtext=findViewById(R.id.Input_text);
        Button submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                todo=TODOtext.getText().toString();
                Intent ret=new Intent();
                ret.putExtra(KEY_1,todo);
                if(todo.isEmpty())setResult(RESULT_CANCELED,ret);
                    else setResult(RESULT_OK,ret);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
