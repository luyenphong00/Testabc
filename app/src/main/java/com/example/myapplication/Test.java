package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.myapplication.database.MyDatabaseHelper;
import com.example.myapplication.database.Student;

public class Test extends AppCompatActivity {

    Button buttonLogin, btnRegister;
    AppCompatEditText edtUsername,edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final MyDatabaseHelper db = new MyDatabaseHelper(this);
        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btn_register);
        buttonLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(edtUsername.getText().toString(),edtPassword.getText().toString());
                db.createAccount(student);
                Log.d("AAAAAA", db.getAllAccount().toString());
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AAAA",   db.loginAccount(new Student(edtUsername.getText().toString()
                        ,edtPassword.getText().toString()))+ "");
                db.deleteAccount();
            }
        });
    }

}
