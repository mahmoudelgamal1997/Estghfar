package com.example2017.android.estghfar;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class elsegl extends AppCompatActivity {

    SharedPreferences sh;

    public   ArrayList<elsegl_item> items;
    DataBaseHelper dataBaseHelper;
ListView listView;
    public  String mdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elsegl);


        dataBaseHelper=new DataBaseHelper(this);

        listView =(ListView)findViewById(R.id.listView);


        ArrayList<elsegl_item> theList = new ArrayList<>();


        Cursor data = dataBaseHelper.getData();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
             theList.add(new elsegl_item("استغفر الله ",data.getString(1),data.getString(2)));
             }
        }

      CustomAdapter c=new CustomAdapter(theList);
      listView.setAdapter(c);

    }


class CustomAdapter extends BaseAdapter{

    ArrayList<elsegl_item> item =new ArrayList<>();

    CustomAdapter(ArrayList<elsegl_item> data){
        this.item=data;

    }




    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i).num;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater infalter=getLayoutInflater();
        View v=infalter.inflate(R.layout.elsegl_item_view,null);

        TextView txtname=(TextView)v.findViewById(R.id.zekr_name);
        TextView txtnum=(TextView)v.findViewById(R.id.zekr_number);
        TextView txtdate=(TextView)v.findViewById(R.id.zekr_time);

        txtname.setText(item.get(i).name);
        txtnum.setText(item.get(i).num);
        txtdate.setText(item.get(i).time);


        return v;
    }
}
}

