package com.example2017.android.estghfar;



import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.preference.ListPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    public static final String Default = "N/A";
    int count = 0;
    TextView text1;
    Button btn;
    int x = 0;
    int inputnum2;
    SharedPreferences sh;
    EditText input;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMediaPlayer = new MediaPlayer();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7819737441034557/8624191221");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        input = (EditText) findViewById(R.id.editText3);
        text1 = (TextView) findViewById(R.id.text1);
        btn = (Button) findViewById(R.id.btn_click);

        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);


        mMediaPlayer = MediaPlayer.create(this, R.raw.ss);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(false);
        mMediaPlayer.start();

    }

    /*
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);

            String s ="" ;

            if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE) {

    s ="Landscape orientation\n";
            }            else if (newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
                s ="Portrait orientation\n";

                }

            s+="onconfig was called "+(()getApplicationContext()).inc()+"times";
            }


    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);


        return true;

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.id_segl:
                Intent intent = new Intent(this, elsegl.class);
                startActivity(intent);
                break;


            case R.id.id_about:
                Intent intent1 = new Intent(this, about.class);
                startActivity(intent1);
                break;


            case R.id.reload:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                // set title
                alertDialogBuilder.setTitle("Warning");

                // set dialog message
                alertDialogBuilder
                        .setMessage("هل تريد اعاده البدء من الصفر")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close

                                // current activity

                                count = 0;
                                text1.setText(String.valueOf(count));
                                btn.setBackgroundResource(R.drawable.btn1);


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
        }


        return super.onOptionsItemSelected(item);
    }


    public void butalarm(View view) {


        if (x == 0) {

            input.setVisibility(View.VISIBLE);
            x++;
        } else {

            input.setVisibility(View.INVISIBLE);
            x--;
        }
    }


    public void clik(View view) {

/*
        inputnum = sh.getString("data", Default);


         inputnum2 = Integer.parseInt(inputnum);
*/


        if (input.getText().toString().equals("")) {


            inputnum2 = 999999999;

        } else {
            inputnum2 = Integer.parseInt(String.valueOf(input.getText()));
        }

        count = count + 1;
        text1.setText(String.valueOf(count));


        // write a parameter control the bottomshape .it recieve it's value from setting by sharedpreference

        if (count == 10) {
            btn.setBackgroundResource(R.drawable.btn2);
        }
        if (count == 20) {
            btn.setBackgroundResource(R.drawable.btn3);
        }


        if (count == inputnum2) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("لقد وصلت للرقم المطلوب!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);
        SharedPreferences.Editor mydata = sh.edit();
        mydata.putString("data", text1.getText().toString());

        mydata.commit();




    }


}