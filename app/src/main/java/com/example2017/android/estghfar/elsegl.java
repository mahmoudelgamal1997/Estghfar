package com.example2017.android.estghfar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class elsegl extends AppCompatActivity {

    SharedPreferences sh;

    public   ArrayList<elsegl_item> items;

    public  String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elsegl);
//time and date
        final Calendar c = Calendar.getInstance();

        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

  final    String t =year+"-"+(month+1)+"-"+day;
m=t;

         items = new ArrayList<elsegl_item>();
        sh=getSharedPreferences("plz", Context.MODE_PRIVATE );

      final String num= (sh.getString( "data","emputy" ) );


        ListView ls = (ListView) findViewById(R.id.listView);
        items.add(new elsegl_item("استغفر الله ",num,t));

        mycustomAdapter mycustomAdapter = new mycustomAdapter(items);
        ls.setAdapter(mycustomAdapter);


    }


    class mycustomAdapter extends BaseAdapter {

// ListItem is the class's name that we made with mainactivity

// we make array it's name is Items and we receive it from customadapter (the class extends baseAdapter).

        ArrayList<elsegl_item> Items = new ArrayList<elsegl_item>();

        mycustomAdapter(ArrayList<elsegl_item> mydata) {

            this.Items = mydata;

        }


        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int postion) {
            return Items.get(postion).name;
        }

        @Override
        public long getItemId(int postion) {
            return postion;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater linflater = getLayoutInflater();
            View view1 = linflater.inflate(R.layout.elsegl_item_view, null);
            TextView txtname = (TextView) view1.findViewById(R.id.zekr_name);
            TextView txtnum = (TextView) view1.findViewById(R.id.zekr_number);
            TextView txtTime = (TextView) view1.findViewById(R.id.zekr_time);
            txtname.setText(Items.get(i).name);
            txtTime.setText(Items.get(i).time);
            txtnum.setText(Items.get(i).num);
            return view1;
        }
// if u add an image add ihis code
// add iamgeView
//image = setImage


        }}