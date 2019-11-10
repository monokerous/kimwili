package com.example.jeannebertoux.kimwili.Controller;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;
import com.example.jeannebertoux.kimwili.Session;

import java.util.List;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 */
public class SessionSelectedActivity extends AppCompatActivity {

    /**
     * Permet d'avoir toutes les données contenue dans une session
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_selected);

        // db instance
        final Dal dal = new Dal(this);
        dal.open();

        // initialisation des champs textView de la vue Session selectionner

        ImageButton btnHome = findViewById(R.id.btnHome);
         EditText uneActivite = findViewById(R.id.et_activite);
         EditText uneDate = findViewById(R.id.date);
         EditText unNbKmParcouru = findViewById(R.id.Km_Parcouru);
         EditText unNiveauDouleur = findViewById(R.id.et_niveau_douleur);
         EditText uneDureeSession = findViewById(R.id.et_duree_session);

        String data= getIntent().getStringExtra("Session");
        Session s = dal.getSessionFromDate(data);
        uneActivite.setText(data);



        List<Session> lesSessions = dal.getSessions();

        for (Session session : lesSessions) {

            uneDate.setText(session.toStringDateDebut() + session.toStringDateFin() + "\n\n");
            unNbKmParcouru.setText(session.getKmParcourus() + " Km " + "\n\n");
            unNiveauDouleur.setText("Douleur : " + session.getNiveauDouleur() + " /10" + "\n\n");

        }
        // récuperer la valeur du spinner pour le nom de chaque activités

    /*    Bundle bundle = getIntent().getExtras();
        String data = bundle.get("data").toString();
        uneActivite.setText(data);


        // selectionner une session en fonction de l'activité selectionnée

        Dal dal = new Dal(this);
        dal.open();
          List<Session> lesSessions = dal.getSessions();

        for (Session session : lesSessions) {

            uneDate.setText(session.toStringDateDebut() + session.toStringDateFin() + "\n\n");
            unNbKmParcouru.setText(session.getKmParcourus() + " Km " + "\n\n");
            unNiveauDouleur.setText("Douleur : " + session.getNiveauDouleur() + " /10" + "\n\n");

        }

            /*    switch (data) {

                    case "Course":
                        for (Session session : lesSessions) {

                            uneDate.setText(session.toStringDateDebut() + session.toStringDateFin() + "\n\n");
                            unNbKmParcouru.setText(session.getKmParcourus() + " Km " + "\n\n");
                            unNiveauDouleur.setText("Douleur : " + session.getNiveauDouleur() + " /10" + "\n\n");

                        }

                        break;
                    case "Marche":
                        for (Session session : lesSessions) {

                            uneDate.setText(session.toStringDateDebut() + session.toStringDateFin() + "\n\n");
                            unNbKmParcouru.setText(session.getKmParcourus() + " Km " + "\n\n");
                            unNiveauDouleur.setText("Douleur: " + session.getNiveauDouleur() + " /10" + "\n\n");

                        }
                        break;
                    case "Natation":
                        for (Session session : lesSessions) {
                            uneDate.setText(session.toStringDateDebut() + session.toStringDateFin() + "\n\n");
                            unNbKmParcouru.setText(session.getKmParcourus() + " Km " + "\n\n");
                            unNiveauDouleur.setText("Douleur: " + session.getNiveauDouleur() + " /10" + "\n\n");

                        }
                        break;
                    case "Vélo":
                        for (Session session : lesSessions) {
                            uneDate.setText(session.toStringDateDebut() + session.getDateFin() + "\n\n");
                            unNbKmParcouru.setText(session.getKmParcourus() + " Km " + "\n\n");
                            unNiveauDouleur.setText("Douleur: " + session.getNiveauDouleur() + " /10" + "\n\n");

                        }
                        break;
                    default:
                        uneActivite.setText("donnée non trouvé ! ");
                }
        dal.close();
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessionSelectedActivity.this, Accueil.class);
                startActivity(intent);
            }
        });*/

    }
}

