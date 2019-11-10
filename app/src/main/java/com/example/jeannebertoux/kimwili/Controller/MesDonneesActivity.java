package com.example.jeannebertoux.kimwili.Controller;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;
import com.example.jeannebertoux.kimwili.Utilisateur;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * Le type Mes donnees activity.
 */
public class MesDonneesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_mes_donnees);

        ImageButton btHome = findViewById(R.id.btHome);
        Button btAjouter = findViewById(R.id.btAjouter);
        final EditText etNom = findViewById(R.id.etNom);
        final EditText etPrenom = findViewById(R.id.etPrenom);
        final EditText etPoids = findViewById(R.id.etPoids);

        // Database instance
        final Dal dal = new Dal(this);
        dal.open();

        // get current user
        final Utilisateur user = dal.getUtilisateur(1);

        etNom.setText(user.getNom());
        etPrenom.setText(user.getPrenom());
        etPoids.setText(Float.toString(user.getPoids()));

        // go to home
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dal.close();
                Intent intent = new Intent(MesDonneesActivity.this, Accueil.class);
                startActivity(intent);
            }
        });

        // Ajouter mes données
        btAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setNom(etNom.getText().toString());
                user.setPrenom(etPrenom.getText().toString());
                user.setPoids(Float.parseFloat(etPoids.getText().toString()));
                dal.updateUtilisateur(1, user);

                dal.close();
                Intent intent = new Intent(MesDonneesActivity.this, Accueil.class);
                startActivity(intent);
            }
        });
    }
}
