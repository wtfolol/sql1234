package com.example.ws.sql1234;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    EditText UsernameEt, PasswordEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        if(username.matches("")){
            Toast.makeText(this,"Username is empty",Toast.LENGTH_LONG).show();
        }
        else if(password.matches("")){
            Toast.makeText(this,"Password is empty",Toast.LENGTH_LONG).show();
        }
        else {
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker((Context) MainActivity.this, username);
            backgroundWorker.execute(type, username, password);
        }
    }

    }


