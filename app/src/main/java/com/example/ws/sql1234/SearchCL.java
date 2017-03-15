package com.example.ws.sql1234;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchCL extends ArrayAdapter<String> {
    private String[] urls;
    private Bitmap[] bitmaps;
    private String[] booknames;
    private String[] bookdescs;
    private String[] abookstt;

    private Activity context;

    public SearchCL(Activity context, String[] urls, Bitmap[] bitmaps, String[] booknames,String[] bookdescs, String[] abookstt) {
        super(context, R.layout.image_list_view, urls);
        this.context = context;
        this.urls= urls;
        this.bitmaps= bitmaps;
        this.booknames = booknames;
        this.bookdescs = bookdescs;
        this.abookstt = abookstt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.image_list_view, null, true);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded);
        TextView textViewBookname = (TextView) listViewItem.findViewById(R.id.textViewBookname);
        textViewBookname.setText(booknames[position]);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],200,200,false));
        return  listViewItem;
    }
}