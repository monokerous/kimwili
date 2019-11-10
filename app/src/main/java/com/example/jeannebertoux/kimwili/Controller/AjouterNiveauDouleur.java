package com.example.jeannebertoux.kimwili.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.NiveauDouleur;
import com.example.jeannebertoux.kimwili.NiveauDouleurException;
import com.example.jeannebertoux.kimwili.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Le type Ajouter niveau douleur.
 *
 * @author Jeanne Bertoux et Kilian Corbel
 */
public class AjouterNiveauDouleur extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_niveau_douleur);

        Button btn_ajouter = findViewById(R.id.btn_ajouter);
        ImageButton btnHome = findViewById(R.id.btnHome);
        final EditText etNiveau = findViewById(R.id.etNiveau);
        final Spinner emplacement = findViewById(R.id.spinnerEmplacement);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerEmplacement, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        emplacement.setAdapter(adapter);

        // Database instance
        final Dal dal = new Dal(this);
        dal.open();


        // Ajouter un niveau de douleur
        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    dal.insertNiveauDouleur(new NiveauDouleur(0, new Date(), Integer.parseInt(etNiveau.getText().toString()), emplacement.getSelectedItem().toString()));
                }
                catch (NiveauDouleurException de){
                    de.toString();
                }
                dal.close();
                Intent intent = new Intent(AjouterNiveauDouleur.this, NiveauDouleurActivity.class);
                startActivity(intent);
            }
        });

        // go to home
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dal.close();
                Intent intent = new Intent(AjouterNiveauDouleur.this, Accueil.class);
                startActivity(intent);
            }
        });
    }
}
