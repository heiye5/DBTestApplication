package com.example.dbtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dbtestapplication.dao.SQLiteDao;
import com.example.dbtestapplication.pojo.Users;
import com.example.dbtestapplication.utils.ToastShowUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_queryall;
    private Button btn_add;
    private Button btn_delete;
    private Button btn_querybyid;
    private TextView show;
    private Context context = this;
    private EditText findId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_queryall = findViewById(R.id.btn_queryall);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_querybyid = findViewById(R.id.btn_querybyid);
        show = findViewById(R.id.show);
        findId = findViewById(R.id.findId);

        onClick(btn_queryall);
        onClick(btn_add);
        onClick(btn_delete);
        onClick(btn_querybyid);

        showText();
    }

    //显示数据
    private void showText() {
        SQLiteDao sqLiteDao = new SQLiteDao(context);
        List<Users> usersList = new ArrayList<>();

        String tmp = "";
        usersList = sqLiteDao.queryAllUsers();
        for (Users users : usersList) {
            tmp += users.getId() + " " + users.getName() + " " + users.getSex() + " "  + users.getAge() + "\n";
        }
        show.setText(tmp);
    }

    private void onClick(View control) {
        control.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDao sqLiteDao = new SQLiteDao(context);

            if(view == btn_queryall){
                showText();

                String message = "刷新";
                ToastShowUtil.show(context,message);

            }
            if(view == btn_add){
                String message = "添加数据";
                ToastShowUtil.show(context,message);

                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                showText();
            }
            if(view == btn_delete){
                String message = "删除数据";
                ToastShowUtil.show(context,message);

                Intent intent = new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(intent);
                showText();
            }
            if(view == btn_querybyid){
                Users users = new Users();
                String message = null;

                if(findId.getText().toString().isEmpty()){
                    message = "输入框为空，正在查询全部数据...";
                    showText();
                }else{
                    int id = Integer.parseInt(findId.getText().toString());
                    message = "正在查询id为" + id + "的数据...";
                    users = sqLiteDao.queryUsersById(id);
                    try{
                        show.setText(users.getId() + " " + users.getName() + " " + users.getSex() + " "  + users.getAge());
                    }catch (Exception e) {
                        show.setText("未找到与该id符合的数据");
                    }
                }
                ToastShowUtil.show(context,message);
            }
        }
    };
}