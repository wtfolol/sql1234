package com.example.ws.sql1234;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetbList {

    public static String[] booknames;
    public static String [] abookrdate;
    public static String [] abookid;
    public static String [] abookprol;



    public static final String JSON_ARRAY="result";
    public static final String BOOK_NAME = "bookname";
    public static final String BOOK_RDATE= "bookrdate";
    public static final String BOOK_ID= "bookid";
    public static final String BOOK_PROL= "bookprol";



    private String json;
    public static JSONArray urls;

    public GetbList(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getblist() throws JSONException {

        booknames = new String[urls.length()];
        abookrdate = new String[urls.length()];
        abookid = new String[urls.length()];
        abookprol = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            booknames[i] = urls.getJSONObject(i).getString(BOOK_NAME);
            abookrdate[i] = urls.getJSONObject(i).getString(BOOK_RDATE);
            abookid[i] = urls.getJSONObject(i).getString(BOOK_ID);
            abookprol[i] = urls.getJSONObject(i).getString(BOOK_PROL);

        }
    }
}