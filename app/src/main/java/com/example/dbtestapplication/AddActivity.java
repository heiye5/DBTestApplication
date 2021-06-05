package com.example.dbtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dbtestapplication.dao.SQLiteDao;
import com.example.dbtestapplication.pojo.Users;
import com.example.dbtestapplication.utils.ToastShowUtil;

public class AddActivity extends AppCompatActivity {
    private Button btn_add;
    private Button cancel;
    private EditText id;
    private EditText name;
    private EditText sex;
    private EditText age;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_add = findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);

        btn_add.setOnClickListener(listener);
        cancel.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btn_add){
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                SQLiteDao sqLiteDao = new SQLiteDao(context);
                Users users = new Users();

                users.setName(name.getText().toString());
                users.setSex(sex.getText().toString());
                users.setAge(Integer.parseInt(age.getText().toString()));
                if(id.getText().toString() == null || id.getText().toString().isEmpty()){
                    sqLiteDao.insert(users);
//                    ToastShowUtil.show(context,"null");
                }else{
                    users.setId(Integer.parseInt(id.getText().toString()));
                    sqLiteDao.insertAndId(users);
//                    ToastShowUtil.show(context,"null");
                }

                ToastShowUtil.show(AddActivity.this,"添加");
                startActivity(intent);
            }

            if(view == cancel){
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
                ToastShowUtil.show(context,"取消");
            }
        }
    };
}