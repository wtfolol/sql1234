package com.example.ws.sql1234;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetaList {

    public static String[] booknames;
    public static String [] abookrdate;
    public static String [] abookid;
    public static String [] abookstt;
    public static String [] abookcate;
    public static String [] abookpub;
    public static String [] abookdes;



    public static final String JSON_ARRAY="result";
    public static final String BOOK_NAME = "bookname";
    public static final String BOOK_RDATE= "bookrdate";
    public static final String BOOK_ID= "bookid";
    public static final String BOOK_STT= "bookstt";
    public static final String BOOK_CATE= "bookcate";
    public static final String BOOK_DES= "bookdes";
    public static final String BOOK_PUB= "bookpub";



    private String json;
    public static JSONArray urls;

    public GetaList(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getalist() throws JSONException {

        booknames = new String[urls.length()];
        abookrdate = new String[urls.length()];
        abookid = new String[urls.length()];
        abookstt = new String[urls.length()];
        abookpub = new String[urls.length()];
        abookdes = new String[urls.length()];
        abookcate = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            booknames[i] = urls.getJSONObject(i).getString(BOOK_NAME);
            abookrdate[i] = urls.getJSONObject(i).getString(BOOK_RDATE);
            abookid[i] = urls.getJSONObject(i).getString(BOOK_ID);
            abookstt[i] = urls.getJSONObject(i).getString(BOOK_STT);
            abookpub[i] = urls.getJSONObject(i).getString(BOOK_PUB);
            abookdes[i] = urls.getJSONObject(i).getString(BOOK_DES);
            abookcate[i] = urls.getJSONObject(i).getString(BOOK_CATE);

        }
    }
}