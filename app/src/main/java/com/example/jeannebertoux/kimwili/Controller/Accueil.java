package com.example.jeannebertoux.kimwili.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.jeannebertoux.kimwili.*;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * Le type Accueil.
 */
public class Accueil extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //initialisation des boutons
        Button btn_Activite = findViewById(R.id.btnActivite);
        Button btn_session = findViewById(R.id.btnSession);
        Button btn_mes_donnees = findViewById(R.id.btnDonnees);
        Button btn_niveau_douleur = findViewById(R.id.btnNiveauDouleur);

        //region Database initialisation
      /*  Dal dal = new Dal(this);
        dal.open();

        //region Create Activites
        Activite marche = new Activite();
        Activite course = new Activite();
        Activite velo = new Activite();
        Activite natation = new Activite();
        try
        {
            marche = new Activite(1, "Marche", "marche.png");
            course = new Activite(2, "Course à pied", "course.png");
            velo = new Activite(3, "Vélo", "velo.png");
            natation = new Activite(4, "Natation", "natation.png");
        }
        catch (ActiviteException e)
        {

        }
        //endregion

        //region Create Niveau douleur
        NiveauDouleur n1 = new NiveauDouleur();
        NiveauDouleur n2 = new NiveauDouleur();
        NiveauDouleur n3 = new NiveauDouleur();
        NiveauDouleur n4 = new NiveauDouleur();

        try
        {
            n1 = new NiveauDouleur(1, new Date(2018,01,22), 8, "Cervicales");
            n2 = new NiveauDouleur(2, new Date(2018,03,23), 6, "Haut du dos");
            n3 = new NiveauDouleur(3, new Date(2018,06,04), 3, "Bas du dos");
            n4 = new NiveauDouleur(4, new Date(2018,07,14), 10, "Tête");
        } catch (NiveauDouleurException e){

        }
        //endregion

        //region Create Sessions
        Session s1 = new Session();
        Session s2 = new Session();
        Session s3 = new Session();
        Session s4 = new Session();

        try{
            s1 = new Session(1, new Date(2018,03,18, 12, 15,0), new Date(2018, 3,18,13,0,0), 8, 7, velo);
            s2 = new Session(2, new Date(2018, 2,10,18,30,00), new Date(2018, 2, 10, 19, 30, 00), 12, 10, natation);
            s3 = new Session(3, new Date(2018, 7,8,10,15,00), new Date(2018, 7 ,8,13,10,5),35, 8, course);
            s4 = new Session(4, new Date(2018, 10,28,16,00,00), new Date(2018, 10, 28, 18, 30 ,00), 15, 7, marche);
        } catch (SessionException se)
        {

        }

        //endregion

        //region Create Utilisateur
        Utilisateur user = new Utilisateur();

        try{
            user = new Utilisateur(1, "Corbel", "Kilian",72);
        }
        catch (UtilisateurException ue){

        }
        //endregion

        //region Insert

        dal.insertActivite(marche);
        dal.insertActivite(course);
        dal.insertActivite(velo);
        dal.insertActivite(natation);

        dal.insertNiveauDouleur(n1);
        dal.insertNiveauDouleur(n2);
        dal.insertNiveauDouleur(n3);
        dal.insertNiveauDouleur(n4);

        dal.insertSession(s1);
        dal.insertSession(s2);
        dal.insertSession(s3);
        dal.insertSession(s4);

        dal.insertUtilisateur(user);

        //endregion

        dal.close();*/

        //actions sur les boutons
        btn_Activite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // "Je veux démarrer la fenetre nommée Activite rattaché à mon activité actuelle (Accueil)"
                Intent intent = new Intent(Accueil.this, ActiviteActivity.class);
                startActivity(intent);
            }
        });
        btn_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, SessionActivity.class);
                startActivity(intent);
            }
        });

        btn_mes_donnees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, MesDonneesActivity.class);
                startActivity(intent);
            }
        });
        btn_niveau_douleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Accueil.this, NiveauDouleurActivity.class);
                startActivity(i);
            }
        });
    }
}
