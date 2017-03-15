package com.example.ws.sql1234;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMember extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member);
    }

    public void GRegister(View v) {
        EditText RNameet = (EditText) findViewById(R.id.etRName);
        EditText RUsernameet = (EditText) findViewById(R.id.etRUsername);
        EditText RPasswordet = (EditText) findViewById(R.id.etRPassword);
        EditText RAgeet = (EditText) findViewById(R.id.etRAge);
        EditText RAdminpet = (EditText) findViewById(R.id.etRAdminp);
        EditText RCPasswordet = (EditText) findViewById(R.id.etRCPassword);
        String Rusername = RUsernameet.getText().toString();
        String Rname = RNameet.getText().toString();
        String Rpassword = RPasswordet.getText().toString();
        String RCpassword = RCPasswordet.getText().toString();
        String Rage = RAgeet.getText().toString();
        String Radminp = RAdminpet.getText().toString();
        if (Rusername.matches("") || Rname.matches("") || Rpassword.matches("") || Rage.matches("")
                || Radminp.matches("")||RCpassword.matches("")) {
            Toast t = Toast.makeText(this, "All field must be filled.", Toast.LENGTH_LONG);
            t.show();
        } else if(Rpassword.matches(RCpassword)) {
            String type = "register";
            RegisterBG registerBG = new RegisterBG((Context) RegisterMember.this);
            registerBG.execute(type, Rname, Rusername, Rpassword, Rage, Radminp);
        }
        else {
            Toast t = Toast.makeText(this, "Password and confirm password are different."
                    , Toast.LENGTH_LONG);
            t.show();
        }
    }
}
