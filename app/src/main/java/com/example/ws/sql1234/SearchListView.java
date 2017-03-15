package com.example.ws.sql1234;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchListView extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    public static final String GET_IMAGE_URL="http://xwl.net23.net/search.php";

    public GetSearch getSearch;

    public static final String BITMAP_ID = "BITMAP_ID";

    EditText SearchET;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list_view);
        SearchET = (EditText)findViewById(R.id.etSearch);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        String value = getIntent().getExtras().getString("parseuname");
        name = value;
    }

    public void OnSearch(View view) {
        String search = SearchET.getText().toString();
        getURLs(search);


    }

    private void getImages(){
        class GetImages extends AsyncTask<Void,Void,Void>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SearchListView.this,"Searching for books..","Be patient...",false,false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                SearchCL searchCL = new SearchCL(SearchListView.this, GetSearch.imageURLs, GetSearch.bitmaps, GetSearch.booknames, GetSearch.bookdescs, GetSearch.abookstt);
                listView.setAdapter(searchCL);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    getSearch.getSearch();

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
                loading = ProgressDialog.show(SearchListView.this,"Loading...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.trim().equals("Fail")) {
                    Toast.makeText(SearchListView.this, "No search result found, please try other.", Toast.LENGTH_LONG).show();
                } else {
                    getSearch = new GetSearch(s);
                    getImages();
                }
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
        gu.execute(GET_IMAGE_URL,search);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ViewBookDetails.class);
        intent.putExtra(BITMAP_ID,i);
        startActivity(intent);
   }

    public void gback(View view) {
        Intent i = new Intent(this, MainMenu.class);
        i.putExtra("parseUsername",name);
        startActivity(i);
    }
}