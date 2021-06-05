package com.example.dbtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dbtestapplication.dao.SQLiteDao;
import com.example.dbtestapplication.utils.ToastShowUtil;

public class DeleteActivity extends AppCompatActivity {
    private Button submit;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        submit = findViewById(R.id.submit);
        id = findViewById(R.id.id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteActivity.this,MainActivity.class);
                SQLiteDao sqLiteDao = new SQLiteDao(DeleteActivity.this);

                int idInt = Integer.parseInt(id.getText().toString());
                sqLiteDao.delete(idInt);

                ToastShowUtil.show(DeleteActivity.this,"删除");
                startActivity(intent);
            }
        });
    }
}