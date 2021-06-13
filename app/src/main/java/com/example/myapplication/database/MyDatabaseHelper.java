package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Note;
import com.example.myapplication.database.Student;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, "Manager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "CREATE TABLE ACCOUNT " +
                "(id INTEGER PRIMARY KEY," +
                "username TEXT," +
                "password TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + "ACCOUNT");
////
//        // Create tables again
        onCreate(db);
    }

    public void createAccount(Student account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());

        // Inserting Row
        db.insert("ACCOUNT", null, values);

        // Closing database connection
        db.close();
    }

    public Student loginAccount(Student student){
        Student exitsStudent = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ACCOUNT WHERE username = '"+student.getUsername()+"' and password = '"+student.getPassword()+"'", null);
        if (c.moveToFirst()){
            exitsStudent = new Student();
            exitsStudent.setUsername(c.getString(1));
            exitsStudent.setPassword(c.getString(2));
        }
        Log.d("AAAAA", getAllAccount().toString());
        return exitsStudent;
    }

    public List<Student> getAllAccount() {

        List<Student> listAccount = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM ACCOUNT";
        SQLiteDatabase db = this.getWritableDatabase();
        // Lay du lieu trong bang
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setUsername(cursor.getString(1));
                student.setPassword(cursor.getString(2));
                listAccount.add(student);
            } while (cursor.moveToNext());
        }

        // return note list
        return listAccount;
    }

}
