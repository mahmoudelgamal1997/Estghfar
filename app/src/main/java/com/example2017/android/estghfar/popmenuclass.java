package com.example2017.android.estghfar;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.jar.Attributes;

/**
 * Created by M7moud on 13-Feb-17.
 */
public class popmenuclass extends DialogFragment implements View.OnClickListener {

   
    View form;
    EditText input;
    Button ok;
    SharedPreferences sh;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        form = inflater.inflate(R.layout.popform, container, false);
        input=(EditText)form.findViewById(R.id.editText);
        ok = (Button)form.findViewById(R.id.butok);

        ok.setOnClickListener(this) ;

        return form;

    }
    @Override
    public void onClick(View view) {
         MainActivity ma = (MainActivity) getActivity();

        sh=ma.getSharedPreferences("plz", Context.MODE_PRIVATE );

        Button bu=(Button)view;

        if (bu.getText().toString().equals("ok")){
            SharedPreferences.Editor  mydata=sh.edit();
            mydata.putString( "data",input.getText().toString() );
            mydata.commit();





            // MY_PREFS_NAME - a static String variable like:
//public static final String MY_PREFS_NAME = "MyPrefsFile";
// MY_PREFS_NAME - a static String variable like:
//public static final String MY_PREFS_NAME = "MyPrefsFile";

          this.dismiss();

    //   MainActivity ma = (MainActivity) getActivity();
      //      ma.getinput(inputnum1);
        }
    }

    }

