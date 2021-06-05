package com.example.dbtestapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dbtestapplication.pojo.Users;
import com.example.dbtestapplication.utils.DBSQLiteTest;
import com.example.dbtestapplication.utils.ToastShowUtil;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDao {
    Context context = null;
    DBSQLiteTest dbsqLite = null;

    public SQLiteDao(Context context) {
        this.context = context;
        this.dbsqLite = new DBSQLiteTest(context,1);
//        ToastShowUtil.show(context,"正在创建...");
    }

    /**
     * 添加数据
     */
    public void insert(Users users){
        SQLiteDatabase database = null;

        try{
            database = dbsqLite.getWritableDatabase();
//            String sql = "insert into users (id,name,sex,age) values (?,?,?,?)";
//            database.execSQL(sql,new Object[]{users.getId(),users.getName(),users.getSex(),users.getAge()});

            String sql = "insert into users (name,sex,age) values (?,?,?)";
            database.execSQL(sql,new Object[]{users.getName(),users.getSex(),users.getAge()});
        }catch(Exception e){
            System.out.println("数据添加失败:" + e);
        }finally {
            database.close();
        }
    }

    /**
     * 添加数据（包括id）
     */
    public void insertAndId(Users users){
        SQLiteDatabase database = null;

        try{
            database = dbsqLite.getWritableDatabase();

            String sql = "insert into users (id,name,sex,age) values (?,?,?,?)";
            database.execSQL(sql,new Object[]{users.getId(),users.getName(),users.getSex(),users.getAge()});
        }catch(Exception e){
            ToastShowUtil.show(context,"数据添加失败，不能重复添加id");
            System.out.println("数据添加失败:" + e);
        }finally {
            database.close();
        }
    }


    /**
     * 删除数据
     */
    public void delete(int id){
        SQLiteDatabase database = null;

        try{
            database = dbsqLite.getWritableDatabase();
            String sql = "delete from users where id = ?";
            database.execSQL(sql,new Object[]{id});
        }catch(Exception e){
            System.out.println("删除失败:" + e);
        }finally {
            database.close();
        }
    }

    /**
     * 按id查询数据
     */
    public Users queryUsersById(int id){
        SQLiteDatabase database = null;
        Users users = null;
        Cursor cursor = null;

        try{
            database = dbsqLite.getReadableDatabase();
            String sql = "select * from users where id = ?";
            cursor = database.rawQuery(sql,new String[]{id+""});

            while(cursor.moveToNext()){
                users = new Users();
                users.setId(cursor.getInt(cursor.getColumnIndex("id")));
                users.setName(cursor.getString(cursor.getColumnIndex("name")));
                users.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                users.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            }
        }catch (Exception e){
            System.out.println("按id数据查询失败" + e);
        }finally {
            database.close();
        }

        return users;
    }

    /**
     * 查询所有数据
     */
    public List<Users> queryAllUsers(){
        SQLiteDatabase sqLiteDatabase = null;
        List<Users> usersList = new ArrayList<>();
        Users users = null;
        Cursor cursor = null;

        try{
            sqLiteDatabase = dbsqLite.getReadableDatabase();
            String sql = "select * from users";
            cursor = sqLiteDatabase.rawQuery(sql,null);
            while(cursor.moveToNext()){
                users = new Users();

                users.setId(cursor.getInt(cursor.getColumnIndex("id")));
                users.setName(cursor.getString(cursor.getColumnIndex("name")));
                users.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                users.setAge(cursor.getInt(cursor.getColumnIndex("age")));

                usersList.add(users);
            }
        }catch (Exception e){
            System.out.println("查询全部数据失败:" + e);
        }finally {
            sqLiteDatabase.close();
        }

        return usersList;
    }


}
