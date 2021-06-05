package com.example.dbtestapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBSQLiteTest extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";         //数据库名
    private static final String TABLE_NAMAE = "users";              //数据表名

    //字段
    private static final String TBALE_COLUMN_ID = "id";
    private static final String TBALE_COLUMN_NAME = "name";
    private static final String TBALE_COLUMN_SEX = "sex";
    private static final String TBALE_COLUMN_AGE = "age";

    public DBSQLiteTest(Context context,int version) {
        super(context, DATABASE_NAME, null, version);               //创建数据库
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("create table if not exists ");
        buffer.append(TABLE_NAMAE + "(");
        buffer.append(TBALE_COLUMN_ID + " integer primary key autoincrement,");
        buffer.append(TBALE_COLUMN_NAME + " varchar(20),");
        buffer.append(TBALE_COLUMN_SEX + " varchar(10),");
        buffer.append(TBALE_COLUMN_AGE + " integer)");

        String sql = "create table if not exists users.db(" +
                "name varchar(20)," +
                "sex varchar(10)," +
                "age integer)";

        //执行sql语句
        sqLiteDatabase.execSQL(buffer.toString());
//        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists "  +"users";

        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }



//    private static final String DBNAME = "users.db";
//
//    private static final String TABLE_NAME = "user";
//
//    private static final String TABLE_INFO_COLUM_ID  = "_id";//主键前面一般都带下划线，也可不带
//
//    private static final String TABLE_INFO_COLUM_NAME = "name";
//
//    private static final String TABLE_INFO_COLUM_PWD = "pwd";
//
//    private static final String TABLE_INFO_COLUM_DT = "createtime";
//
//    //本类的构造方法,DBNAME数据库名字，1是数据库版本。
//
//    public DBSQLiteTest(Context context, int version) {
//
//        /*创建构造方法时，默认是以下构造方式，可根据个人需要修改;上面定义了数据库名，故可直接写死DBNAME*/
//
//        super(context, DBNAME, null, version);
//
//    }
//
//    @Override
//
//    public void onCreate(SQLiteDatabase db) {
//
//        //创建表,使用StringBuffer代替String减少内存消耗
//
//        StringBuffer stringBuffer = new StringBuffer();
//
//        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
//
//        stringBuffer.append(TABLE_NAME + "(");
//
//        stringBuffer.append(TABLE_INFO_COLUM_ID+" integer primary key autoincrement ,");
//
//        stringBuffer.append(TABLE_INFO_COLUM_NAME+" varchar(16),");
//
//        stringBuffer.append(TABLE_INFO_COLUM_PWD+" varchar(36),");
//
//        stringBuffer.append(TABLE_INFO_COLUM_DT+" varchar(16))");
//
////create table if not exists user(id integer primary key autoincrement,name varchar(16),pwd varchar(36),createtime varchar(16))
//
//        //执行操作
//
//        db.execSQL(stringBuffer.toString());
//
//    }
//
//    /*重写的onUpgrade方法。
//
//    当数据库结构修改，优化后，需要更新版本时，执行该方法，
//
//    具体就是将旧的数据库删除，重写创建数据库。以达到更新的目的。
//
//    方法中的oldVersion和newVersion分别对应新旧版本，
//
//    可以用户自己定义，系统会自动回调该方法并判断版本是否发生变化*/
//
//    @Override
//
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        String sql = "drop table if exists "  +TABLE_NAME;
//
//        db.execSQL(sql);
//
//        onCreate(db);
//
//    }

}
