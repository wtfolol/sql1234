package com.example.ws.sql1234;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AdminMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_menu);
    }

    public void GPregister(View v) {
        Intent i = new Intent(this, MemberManage.class);
        startActivity(i);
    }

    public void GPCheck(View view) {
        Intent i = new Intent(this, AdminCurrentList.class);
        startActivity(i);
    }

    public void GLogout(View view) {
        Intent i = new Intent(AdminMainMenu.this,MainActivity.class);
        startActivity(i);
    }

    public void GPRegBook(View view) {
        Intent i = new Intent(AdminMainMenu.this,BookManage.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
    }


    public void gPCtask(View view) {
        Intent i = new Intent(AdminMainMenu.this,CirculationTask.class);
        startActivity(i);
    }

    public void gPDmtask(View view) {
        Intent i = new Intent(AdminMainMenu.this,DMrelated.class);
        startActivity(i);
    }
    public void gPam(View view){
        Intent i = new Intent(AdminMainMenu.this,Alarmmanager.class);
        startActivity(i);
    }
}
