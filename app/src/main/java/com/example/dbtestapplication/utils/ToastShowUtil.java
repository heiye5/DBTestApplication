package com.example.dbtestapplication.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastShowUtil {

    //信息显示工具类
    public static void show(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

}
