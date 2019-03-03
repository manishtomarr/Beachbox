package com.example.jesse.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // this is where database has it's function, which you can reuse to connect the MindBody database here.


    public Database(Context context) {
        super(context, "Login.db", null, 1);
    } //^ this part didn't works as I had hoped, I was going to have it as a manual db, but it
    // seems not so secure

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(user text primary key, email text, password text, score double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert( String user, String email, String password, Double score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("score", score);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public Boolean chkuser(String user, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from  user where user=? and password=?",new String[]{user, password});
        if(cursor.getCount()>0) return false;
        else return true;
    }
}
