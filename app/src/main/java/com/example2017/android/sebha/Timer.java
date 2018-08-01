package com.example2017.android.sebha;

import android.os.CountDownTimer;
import android.widget.Toast;

/**
 * Created by M7moud on 04-Mar-18.
 */
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

        Toast.makeText(m.getApplicationContext(),"okkkk",Toast.LENGTH_LONG).show();
    }
}
