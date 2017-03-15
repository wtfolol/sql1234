package com.example.ws.sql1234;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookRenewal extends AppCompatActivity {
    private int pl;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_renewal);
        TextView tvbookrenewal = (TextView)findViewById(R.id.tvBookRenewal);
        int i = getIntent().getExtras().getInt("parseposition");
        String value = getIntent().getExtras().getString("parseuname");
        pl = i;
        name = value;
        tvbookrenewal.setText("Do you sure you want to renew this book? "+
                GetbList.booknames[i]+"\n"+GetbList.abookrdate[i]+"\n"+"Time available to " +
                "renew the date of borrowal left :"+GetbList.abookprol[i]);
        Button btn = (Button)findViewById(R.id.btnRenew);
        btn.setClickable(false);

        if(GetbList.abookprol[i].equals("1")){
            btn.setClickable(true);
        }
    }

    public void GRenew(View view) {
        String type = "BookRenew";
        BookRenewalBG bookrenewalBG = new BookRenewalBG((Context) BookRenewal.this);
        bookrenewalBG.execute(type,GetbList.booknames[pl]);
    }

    public void Gback(View view) {
        Intent i = new Intent(this, MainMenu.class);
        i.putExtra("parseUsername",name);
        startActivity(i);
    }
}
