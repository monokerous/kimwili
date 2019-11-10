package com.example.jeannebertoux.kimwili.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;
import com.example.jeannebertoux.kimwili.Session;
import com.example.jeannebertoux.kimwili.SessionException;

import java.util.Date;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 */
public class SessionStart extends AppCompatActivity {
    // attributs privées
    private long startTime = 0L;
    private long timeMs = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;

    private TextView tvTimer;
    private Handler customHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.session_start);

        // Database instance
        final Dal dal = new Dal(this);
        dal.open();

        //get start time
        final Date dateDebut = new Date();

        // Get items
        final Button btnDemarrer = findViewById(R.id.btnDemarrer);
        final Button btnArreter = findViewById(R.id.btnArreter);
        final ImageButton btnHome = findViewById(R.id.btnHome);
        final Button btnTerminer = findViewById(R.id.btnTerminer);
        final EditText etKmParcourus = findViewById(R.id.etKmParcourus);
        final EditText etNiveauDouleur = findViewById(R.id.etNiveauDouleur);
        tvTimer = findViewById(R.id.tvTimer);

        customHandler = new Handler();

        btnDemarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            }
        });

        btnArreter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff += timeMs;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dal.close();
                Intent intent = new Intent(SessionStart.this, Accueil.class);
                startActivity(intent);
            }
        });

        btnTerminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date dateFin = new Date();
                Intent i = getIntent();
                Activite a = ((Activite)i.getSerializableExtra("Activite"));
                try{
                    dal.insertSession(new Session(0, dateDebut, dateFin, Float.parseFloat(etKmParcourus.getText().toString()), Integer.parseInt(etNiveauDouleur.getText().toString()), a));
                }
                catch (SessionException se)
                {
                    se.toString();
                }
                dal.close();

                // back to home
                Intent intent = new Intent(SessionStart.this, Accueil.class);
                startActivity(intent);
            }
        });
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeMs = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeMs;

            int secs = (int) (updatedTime % 1000);
            int mins = secs/60;
            secs = secs%60;
            int milliseconds = (int) (updatedTime % 1000);

            tvTimer.setText(""+mins+":"+ String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };
}
