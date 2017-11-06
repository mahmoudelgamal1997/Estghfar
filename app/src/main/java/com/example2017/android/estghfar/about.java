package com.example2017.android.estghfar;

import android.content.Intent;
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

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        ArrayList<ListItem> Items= new  ArrayList<ListItem> ();
        Items.add(new ListItem( "أحاديثِ","بعض الاحاديث  " ));
        MyCustomAdapter myadapter = new MyCustomAdapter(Items);
        ListView ls= (ListView)findViewById(R.id.list);
        ls.setAdapter(myadapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override



            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                switch( position )
                {
                    case 0:  Intent Ahades = new Intent(about.this,  Ahades.class);
                        startActivity( Ahades);
                        break;
                    case 1:  Intent Qraan = new Intent(about.this, Qraan.class);
                        startActivity(Qraan);
                        break;
                }

            }
        });
    }





    class MyCustomAdapter extends BaseAdapter {
        ArrayList<ListItem> Items = new ArrayList<ListItem>();

        MyCustomAdapter(ArrayList<ListItem> Items) {
            this.Items = Items;

        }


        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public String getItem(int position) {
            return Items.get(position).Name;

        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.raw_list,null);
            TextView textname = (TextView)row.findViewById(R.id.text_name);
            TextView textdes = (TextView)row.findViewById(R.id.text_desc);
            textname.setText(Items.get(i).Name);
            textdes.setText(Items.get(i).Desc);



            return row;
        }

    }


}
