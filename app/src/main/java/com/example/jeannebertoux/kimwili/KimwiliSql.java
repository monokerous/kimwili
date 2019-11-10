package com.example.jeannebertoux.kimwili;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 */
public class KimwiliSql extends SQLiteOpenHelper {

    // Create table string for each table
    private static final String CREATE_TABLE_ACTIVITE = "CREATE TABLE IF NOT EXISTS Activite(idActivite INTEGER PRIMARY KEY,libelle VARCHAR(100), image VARCHAR(200));";
    private static final String CREATE_TABLE_SESSION = "CREATE TABLE IF NOT EXISTS Session(idSession INTEGER PRIMARY KEY NOT NULL,dateDebut DATE, dateFin DATE, kmParcourus FLOAT, niveauDouleur INT, idActivite INT, FOREIGN KEY (idActivite) REFERENCES Activite(idActivite));";
    private static final String CREATE_TABLE_NIVEAUDOULEUR = "CREATE TABLE IF NOT EXISTS NiveauDouleur(idNiveauDouleur INTEGER PRIMARY KEY NOT NULL, date DATE, niveau INT, emplacement VARCHAR(50));";
    private static final String CREATE_TABLE_UTILISATEUR = "CREATE TABLE IF NOT EXISTS Utilisateur(idUtilisateur INTEGER PRIMARY KEY NOT NULL,nom VARCHAR(100), prenom VARCHAR(100), poids FLOAT, image VARCHAR(200));";

    /**
     * Instantiates a new Kimwili sql.
     *
     * @param context the context
     * @param name    the name
     * @param factory the factory
     * @param version the version
     */
// Create bdd string
    // Constructeur
    public KimwiliSql(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_UTILISATEUR);
        db.execSQL(CREATE_TABLE_ACTIVITE);
        db.execSQL(CREATE_TABLE_NIVEAUDOULEUR);
        db.execSQL(CREATE_TABLE_SESSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE Activite;");
        db.execSQL("DROP TABLE Session;");
        db.execSQL("DROP TABLE NiveauDouleur;");
        db.execSQL("DROP TABLE Utilisateur;");
        onCreate(db);
    }
}
