package com.example.jeannebertoux.kimwili.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.jeannebertoux.kimwili.ActiviteException;
import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * Le type Activite activity.
 */
public class ActiviteActivity extends Activity {

  /*  private ArrayList<Button> arrayList;
    private ArrayAdapter<Button> adapter;
    private ImageButton btn_img;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activite);

        ImageButton btn_ajouter = findViewById(R.id.btn_ajouter);
        ImageButton btnHome = findViewById(R.id.btnHome);
        Button btnActivite = findViewById(R.id.btnActivite);

        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActiviteActivity.this, AjouterActiviteActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActiviteActivity.this, Accueil.class);
                startActivity(intent);
            }
        });

        // Fetch data
        final Dal dal = new Dal(this);
        dal.open();
        List<Activite> activities = dal.getActivites();
        List<String> nomActivites = new ArrayList<>();

        for (Activite a : activities)
        {
            nomActivites.add(a.getLibelle());
        }

        final Spinner spinner = (Spinner)findViewById(R.id.activities);

        // Application de l'array sur le Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, nomActivites);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);



        btnActivite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activite = spinner.getSelectedItem().toString();
                Activite a = dal.getActiviteFromLibelle(activite);
                Intent intent = new Intent(ActiviteActivity.this, SessionStart.class);
                intent.putExtra("Activite", a);
                startActivity(intent);
            }
        });
    }
}
