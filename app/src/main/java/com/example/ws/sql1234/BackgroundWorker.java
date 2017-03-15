package com.example.ws.sql1234;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WS on 9/21/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    private String username;


    Context context;
    public BackgroundWorker (Context context,String username) {

        this.context = context;
        this.username = username;
    }
    @Override

    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://www.xwl.net23.net/login.php";

        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.trim().equals("User")) {
            Toast t = Toast.makeText(context, "Login Status : Success"+"\n"+" Member type :" + result, Toast.LENGTH_LONG);
            t.show();
            Intent i = new Intent(context, MainMenu.class);
            i.putExtra("parseUsername",username);
            context.startActivity(i);

        }

        else if (result.trim().equals("Admin")) {
            Toast t = Toast.makeText(context, "Login Status : Success"+"\n"+" Member type :" + result, Toast.LENGTH_LONG);
            t.show();
            Intent i2 = new Intent(context, AdminMainMenu.class);
            //i.putExtra("parseUsername",username);
            context.startActivity(i2);
        }
        else
        {
        Toast t = Toast.makeText(context,"Login Status :Failed(Incorrect Username/Password.)",
                        Toast.LENGTH_LONG);
                t.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
