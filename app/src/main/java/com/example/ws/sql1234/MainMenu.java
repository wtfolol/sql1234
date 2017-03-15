package com.example.ws.sql1234;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String GET_URL="http://xwl.net23.net/list.php";
    private ListView listView3;
    public GetbList getblist;
    String uName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        String newString = null;
        newString = getIntent().getExtras().getString("parseUsername");
        uName = newString;
        getURLs(newString);
        listView3 = (ListView) findViewById(R.id.listView2);
        listView3.setOnItemClickListener(this);
        Button Gcsearch =(Button)findViewById(R.id.btnGCsSearch);
        Gcsearch.setBackgroundColor(Color.LTGRAY);
        Button Gnsearch =(Button)findViewById(R.id.btnSearch);
        Gnsearch.setBackgroundColor(Color.LTGRAY);
        TextView tvsearch = (TextView)findViewById(R.id.tvSearch);
        TextView tvusername = (TextView)findViewById(R.id.tvusername);
        tvsearch.setTextColor(Color.BLACK);
        String type = "getmember";
        GetMemberBG getMemberBG = new GetMemberBG((Context) MainMenu.this);
        getMemberBG.execute(type, newString);
        String userid = getIntent().getExtras().getString("userid");
        tvusername.setText("userid " +userid);
    }


    private void getImages(){
        class GetImages extends AsyncTask<Void,Void,Void>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainMenu.this,"Getting currently borrowed book..","Be patient...",false,false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                ListCL listCL = new ListCL(MainMenu.this,GetbList.booknames, GetbList.abookrdate,GetbList.abookid);
                listView3.setAdapter(listCL);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    getblist.getblist();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs(String search) {
        class GetURLs extends AsyncTask<String,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainMenu.this,"Loading...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getblist = new GetbList(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    String search = strings[1];
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("search","UTF-8")+"="+URLEncoder.encode(search,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(GET_URL,search);
    }

    public void GSearch(View view) {
        Intent i = new Intent(MainMenu.this,SearchListView.class);
        i.putExtra("parseuname",uName);
        MainMenu.this.startActivity(i);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BookRenewal.class);
        Bundle extras = new Bundle();
        extras.putString("parseuname", uName);
        extras.putInt("parseposition",position);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void GCSearch(View view) {
        Intent i = new Intent(MainMenu.this,SearchClv.class);
        i.putExtra("parseuname",uName);
        MainMenu.this.startActivity(i);
    }

    public void gLogout(View view) {
        Intent i = new Intent(MainMenu.this,MainActivity.class);
        MainMenu.this.startActivity(i);
    }
    public void onBackPressed() {
    }
}
