package com.example.ws.sql1234;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdminclCL extends ArrayAdapter<String> {
    private String[] booknames;
    private String[] abookrdate;
    private String[] abookid;
    private String[] abookstt;

    private Activity context;

    public AdminclCL(Activity context,String[] booknames,String[] abookrdate,String[] abookstt) {
        super(context, R.layout.admin_list_view,booknames );
        this.context = context;
        this.booknames = booknames;
        this.abookrdate = abookrdate;
        this.abookid = abookid;
        this.abookstt = abookstt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.admin_list_view, null, true);
        TextView tvAbkname = (TextView) listViewItem.findViewById(R.id.tvAbkname);
        TextView tvAbkrdate = (TextView) listViewItem.findViewById(R.id.tvAbkrdate);
        TextView tvAbkstt = (TextView) listViewItem.findViewById(R.id.tvAbkstt);
        tvAbkname.setText(booknames[position]);
        tvAbkrdate.setText(abookrdate[position]);
        tvAbkstt.setText(abookstt[position]);
        return  listViewItem;
    }
}