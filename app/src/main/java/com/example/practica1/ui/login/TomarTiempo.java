package com.example.practica1.ui.login;
import com.example.practica1.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class TomarTiempo extends AppCompatActivity {
    Boolean esta = false;
    int ejercicio = 1;
    CountDownTimer a;
    CountDownTimer b;
    MediaPlayer mediaPlayer;
    DecimalFormat objFormato=new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final TextView textView = (TextView) findViewById(R.id.text);
        final TextView textView2 = (TextView) findViewById(R.id.text2);
        final Button empezar = findViewById(R.id.button);
        final Button terminar = findViewById(R.id.button2);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        final PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
         a= new CountDownTimer(50000, 10) {
            public void onTick(long millisUntilFinished) {
                textView.setText("Ejercicio: " +ejercicio);
                double r = (double)millisUntilFinished/1000;
                int milisegundos = (int)(r % 1 *100);
                int segundos = (int)(r-(r % 1));
                textView2.setText(""+segundos+":"+milisegundos);
            }
            public void onFinish() {
                mediaPlayer.start();
                b =new CountDownTimer(15000, 10) {
                    public void onTick(long millisUntilFinished) {
                        textView.setText("Descanso:");
                        double r = (double)millisUntilFinished/1000;
                        int milisegundos = (int)(r % 1 *100);
                        int segundos = (int)(r-(r % 1));
                        textView2.setText(""+segundos+":"+milisegundos);
                    }
                    public void onFinish() {
                        comenzar();
                    }
                }.start();
            }
        };
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!esta) {
                    wakeLock.acquire();
                    mediaPlayer.start();
                    a.start();
                    esta = true;
                }
                }
        });
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ejercicio = 1;
               textView.setText("Hazlo!");
               textView2.setText("00:00");
               a.cancel();
               b.cancel();
               esta=false;
               wakeLock.release();
            }
        });

    }
    public void comenzar(){
        ejercicio++;
        mediaPlayer.start();
        a.start();
    }
}
