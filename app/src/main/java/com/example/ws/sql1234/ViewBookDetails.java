package com.example.ws.sql1234;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewBookDetails extends AppCompatActivity {
    private ImageView imageView;
    private TextView tvBkname;
    private EditText PreMemberidet;
    private int i2;
    private String df2;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_view_book_details);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dfString = df.format(c.getTime());
                df2 = dfString;
                //Toast t = Toast.makeText(ViewBookDetails.this, dfString, Toast.LENGTH_LONG);
                //t.show();
                Button btn =(Button)findViewById(R.id.btnPreborrow) ;
                btn.setClickable(false);
                tvBkname = (TextView) findViewById(R.id.tvBkname);

                Intent intent = getIntent();
                int i = intent.getIntExtra(SearchListView.BITMAP_ID, 0);
                i2 = i ;
                String type = "search";
                ViewBookDetailsBG viewBookDetailsBG = new ViewBookDetailsBG((Context)ViewBookDetails.this);
                viewBookDetailsBG.execute(type,GetSearch.abookid[i]);
                //Toast t = Toast.makeText(ViewBookDetails.this, GetSearch.abookid[i], Toast.LENGTH_LONG);
                //t.show();
                String s = "No";
                if (GetSearch.abookstt[i].equals("0")) {
                    s = "Yes";
                   btn.setClickable(true);
                }
                imageView = (ImageView) findViewById(R.id.imageViewFull);
                imageView.setImageBitmap(GetSearch.bitmaps[i]);
                tvBkname.setText(GetSearch.booknames[i] + "\n\n" + "Availability:" + s
                        +"\n\n"+GetSearch.bookdescs[i]);
            }

    public void GPBorrow(View view) {
        PreMemberidet = (EditText) findViewById(R.id.etPreborrowID);
        String s = PreMemberidet.getText().toString();
        String type = "PreBorrow";
        ViewBookDetailsBG viewBookDetailsBG = new ViewBookDetailsBG((Context)ViewBookDetails.this);
        viewBookDetailsBG.execute(type,GetSearch.abookid[i2],s,df2);

    }
}

