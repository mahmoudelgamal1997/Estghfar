package com.example2017.android.sebha;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    int Ads_Time=12000*1000;
    int count = 0;
    TextView text1;
    Button btn;
    int x = 0;
    int inputnum2;
    SharedPreferences sh;
    EditText input;
    MediaPlayer mMediaPlayer;
    DataBaseHelper dataBaseHelper;
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;
    AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper=new DataBaseHelper(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
         adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

// Prepare the Interstitial Ad
        interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });

        mMediaPlayer = new MediaPlayer();



        input = (EditText) findViewById(R.id.editText3);
        text1 = (TextView) findViewById(R.id.text1);
        btn = (Button) findViewById(R.id.btn_click);

        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);


        mMediaPlayer = MediaPlayer.create(this, R.raw.ss);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(false);
        mMediaPlayer.start();

        start_time();

    }



    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

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


    @Override
    protected void onDestroy() {
        super.onDestroy();


        AddData(text1.getText().toString(),calendre());

    }

    @Override
    protected void onStop() {

        super.onStop();


    }

    public void AddData(String number,String date){

        boolean insertData = dataBaseHelper.AddData(number,date);


    }



    public void start_time()
    {


        Timer timer=new Timer(Ads_Time,1000);
        timer.start();
    }

    public class Timer  extends CountDownTimer {
        MainActivity m =new MainActivity();
        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {

// Prepare the Interstitial Ad
            interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

            interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    // Call displayInterstitial() function
                    displayInterstitial();
                }
            });


            start_time();


        }



}




public String calendre(){

    final Calendar c = Calendar.getInstance();
    final int year = c.get(Calendar.YEAR);
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);

    final String date = year + "-" + (month + 1) + "-" + day;

    return date;
}

}