package com.example.ws.sql1234;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GetSearch {

    public static String[] imageURLs;
    public static Bitmap[] bitmaps;
    public static String[] booknames;
    public static String[] bookprols;
    public static String[] bookdescs;
    public static String [] abookstt;



    public static final String JSON_ARRAY="result";
    public static final String IMAGE_URL = "url";
    public static final String BOOK_NAME = "bookname";
    public static final String BOOK_PROL= "bookprol";
    public static final String BOOK_DESC= "bookdesc";
    public static final String BOOK_STT= "bookstt";




    private String json;
    private JSONArray urls;

    public GetSearch(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(IMAGE_URL));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getAllImages() throws JSONException {
        bitmaps = new Bitmap[urls.length()];
        booknames = new String[urls.length()];
        imageURLs = new String[urls.length()];
        bookprols  = new String[urls.length()];
        bookdescs  = new String[urls.length()];
        abookstt = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            booknames[i] = urls.getJSONObject(i).getString(BOOK_NAME);
            imageURLs[i] = urls.getJSONObject(i).getString(IMAGE_URL);
            bookprols[i] = urls.getJSONObject(i).getString(BOOK_PROL);
            bookdescs[i] = urls.getJSONObject(i).getString(BOOK_DESC);
            abookstt[i] = urls.getJSONObject(i).getString(BOOK_STT);

            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);

        }
    }
}