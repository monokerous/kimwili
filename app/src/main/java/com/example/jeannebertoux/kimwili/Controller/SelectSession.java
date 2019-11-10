package com.example.jeannebertoux.kimwili.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;
import com.example.jeannebertoux.kimwili.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by kilia on 15/04/2018.
 */

public class SelectSession extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_session);

        ImageButton btnHome = findViewById(R.id.btnHome);
        Button btnConsulter = findViewById(R.id.btnConsulter);
        final Spinner spinnerSession = findViewById(R.id.spinnerSession);

        // Database instance
        final Dal dal = new Dal(this);
        dal.open();

        Intent i = getIntent();
        Activite a = ((Activite)i.getSerializableExtra("Activite"));

        final List<Session> lesSessions =  dal.getSessionActivite(a);

        List<String> nomSessions = new ArrayList<String>();
        for (Session s: lesSessions)
        {
        //    String [] session = {s.toStringDateDebut(), s.getDateDebutToString()};
            nomSessions.add(s.toStringDateDebut());
        }

        // Application de l'array sur le Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, nomSessions);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerSession.setAdapter(spinnerArrayAdapter);

        // Definition des boutons
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dal.close();
                Intent intent = new Intent(SelectSession.this, Accueil.class);
                startActivity(intent);
            }
        });

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date d = new Date();
                int test = spinnerSession.getSelectedItemPosition();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Session s = lesSessions.get(test);

                Intent intent = new Intent(SelectSession.this, SessionSelectedActivity.class);
                //Session s = dal.getSessionFromDate());
                intent.putExtra("Session",s.getId());
                startActivity(intent);
            }
        });

    }
}
