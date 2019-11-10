package com.example.jeannebertoux.kimwili.Controller;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;

import java.util.ArrayList;
import java.util.List;


/**
 * le type Session activity.
 *
 * @author Jeanne Bertoux et Kilian Corbel
 */
public class SessionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button selectActivite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);


        // Spinner element
         spinner = (Spinner) findViewById(R.id.spinner);

         selectActivite = findViewById(R.id.btnSelectSession);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();



    //  spinner.setOnItemSelectedListener(new SessionActivity());
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        // database handler
        final Dal dal = new Dal(this);
        dal.open();
        // Spinner Drop down elements
        List<String> lables = dal.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        selectActivite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(SessionActivity.this,SelectSession.class);
                intent.putExtra("Activite",dal.getActiviteFromLibelle(String.valueOf(spinner.getSelectedItem())));
                startActivity(intent);
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}








        /*   final Spinner unSpinner = (Spinner) findViewById(R.id.spinner);

        ImageButton btnHome = findViewById(R.id.btn_Home);

        //création d'un ArrayAdapter en utilisant un ArrayList<String> et une disposition de spinner par défaut
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lst_activite, android.R.layout.simple_spinner_item);
        //Spécification du layout utiliser lorsque la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Appliquer le setAdapter sur le spinner
        unSpinner.setAdapter(adapter);
        // Appliquer le setOnItemSelectedListener sur le spinner
        unSpinner.setOnItemSelectedListener(this);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessionActivity.this, Accueil.class);
                startActivity(intent);
            }
        });

        unSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onClick(View view){

            }
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long row_id){
                Intent intent= new Intent(SessionActivity.this,SessionSelectedActivity.class);
                intent.putExtra("data",String.valueOf(unSpinner.getSelectedItem()));
                startActivity(intent); */


                /*   final Intent intent;
                switch (position) {
                    case 1: // Course
                        String selected = arg0.getItemAtPosition(position).toString();
                        intent = new Intent(SessionActivity.this, SessionSelectedActivity.class);
                        intent.putExtra("getCourse",selected);
                        startActivity(intent);
                        break;
                    case 2: // Marche
                        String select = arg0.getItemAtPosition(position).toString();
                        intent = new Intent(SessionActivity.this, SessionSelectedActivity.class);
                        intent.putExtra("getMarche",select);
                        startActivity(intent);
                        break;
                    case 3:// Vélo
                        String select3 =  arg0.getItemAtPosition(position).toString();
                        intent = new Intent(SessionActivity.this, SessionSelectedActivity.class);
                        intent.putExtra("getVelo",select3);
                        startActivity(intent);
                        break;
                    case 4: //Natation
                        String select4 = arg0.getItemAtPosition(position).toString();
                        intent = new Intent(SessionActivity.this, SessionSelectedActivity.class);
                        intent.putExtra("getNatation",select4);
                        startActivity(intent);
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/



