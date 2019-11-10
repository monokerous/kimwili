package com.example.jeannebertoux.kimwili;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *  @author Jeanne Bertoux et Kilian Corbel
 */
public class Dal extends SQLiteOpenHelper {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "kimwili.db";

    private SQLiteDatabase bdd;

    private KimwiliSql myBdd;

    /**
     * Constructeur de la classe Dal pour la base de donnée
     *
     * @param context the context
     */
    public Dal(Context context)
    {
        super(context, NOM_BDD, null, VERSION_BDD);
        myBdd = new KimwiliSql(context, NOM_BDD, null, VERSION_BDD);
    }

    /**
     * Open.
     */
    public void open()
    {
        bdd = myBdd.getWritableDatabase();
    }

    public void close()
    {
        bdd.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Gets bdd.
     *
     * @return la bdd
     */
    public SQLiteDatabase getBDD()
    {
        return bdd;
    }

    //region Insertion

    /**
     * Insert activite long.
     *
     * @param _activite l' activite
     * @return  insertion dans la bdd SLQLite de l'activite (long)
     */
    public long insertActivite(Activite _activite)
    {
        ContentValues values = new ContentValues();
        values.put("libelle", _activite.getLibelle());
        values.put("image", _activite.getImage());

        return bdd.insert("Activite", null, values);
    }

    /**
     * Insert niveau douleur long.
     *
     * @param _niveauDouleur le niveau douleur
     * @return  insertion dans la bdd SLQLite de niveauDouleur (long)
     */
    public long insertNiveauDouleur(NiveauDouleur _niveauDouleur)
    {
        ContentValues values = new ContentValues();
        values.put("niveau", _niveauDouleur.getNiveau());
        values.put("date", _niveauDouleur.getDate());
        values.put("emplacement", _niveauDouleur.getEmplacement());

        return bdd.insert("NiveauDouleur", null, values);
    }

    /**
     * Insert session long.
     *
     * @param _session la session
     * @return insertion dans la bdd SLQLite de session(long)
     */
    public long insertSession(Session _session)
    {
        ContentValues values = new ContentValues();

        values.put("dateDebut", _session.getDateDebut());
        values.put("dateFin", _session.getDateFin());
        values.put("kmParcourus", _session.getKmParcourus());
        values.put("niveauDouleur", _session.getNiveauDouleur());
        values.put("idActivite", _session.getActivite().getId());

        return bdd.insert("Session", null, values);
    }

    /**
     * Insert utilisateur long.
     *
     * @param _utilisateur l' utilisateur
     * @return  insertion dans la bdd SLQLite de l'utilisateur (long)
     */
    public long insertUtilisateur(Utilisateur _utilisateur)
    {
        ContentValues values = new ContentValues();

        values.put("nom", _utilisateur.getNom());
        values.put("prenom", _utilisateur.getPrenom());
        values.put("poids", _utilisateur.getPoids());

        return bdd.insert("Utilisateur", null, values);
    }

    //endregion

    //region Update

    /**
     * Update activite int.
     *
     * @param _id       l' id
     * @param _activite l' activite
     * @return  int
     */
    public int updateActivite(int _id, Activite _activite)
    {
        ContentValues values = new ContentValues();
        values.put("libelle", _activite.getLibelle());
        values.put("image", _activite.getImage().toString());

        return bdd.update("Activite", values,"idActivite ="+_id, null);
    }

    /**
     * Update niveau douleur int.
     *
     * @param _id            l' id
     * @param _niveauDouleur le niveau douleur
     * @return  int
     */
    public int updateNiveauDouleur(int _id, NiveauDouleur _niveauDouleur)
    {
        ContentValues values = new ContentValues();
        values.put("niveau", _niveauDouleur.getNiveau());
        values.put("date", _niveauDouleur.getDate());
        values.put("emplacement", _niveauDouleur.getEmplacement());

        return bdd.update("NiveauDouleur", values,"idNiveauDouleur ="+_id, null);
    }

    /**
     * Update session int.
     *
     * @param _id      l'id
     * @param _session la session
     * @return  int
     */
    public int updateSession(int _id, Session _session)
    {
        ContentValues values = new ContentValues();

        values.put("dateDebut", _session.getDateDebut());
        values.put("dateFin", _session.getDateFin());
        values.put("kmParcourus", _session.getKmParcourus());
        values.put("niveauDouleur", _session.getNiveauDouleur());

        return bdd.update("Session", values, "idSession ="+_id, null);
    }

    /**
     * Update utilisateur int.
     *
     * @param _id          l' id
     * @param _utilisateur l' utilisateur
     * @return the int
     */
    public int updateUtilisateur(int _id, Utilisateur _utilisateur)
    {
        ContentValues values = new ContentValues();

        values.put("nom", _utilisateur.getNom());
        values.put("prenom", _utilisateur.getPrenom());
        values.put("poids", _utilisateur.getPoids());

        return bdd.update("Utilisateur", values,"idUtilisateur ="+_id, null);
    }

    //endregion

    //region Remove

    /**
     * Remove activite int.
     *
     * @param _id l' id
     * @return  int
     */
    public int removeActivite(int _id)
    {
        return bdd.delete("Activite", "idActivite ="+_id, null);
    }

    /**
     * Remove niveau douleur int.
     *
     * @param _id l' id
     * @return  int
     */
    public int removeNiveauDouleur(int _id)
    {
        return bdd.delete("NiveauDouleur", "idNiveauDouleur ="+_id, null);
    }

    /**
     * Remove session int.
     *
     * @param _id l'id
     * @return  int
     */
    public int removeSession(int _id)
    {
        return bdd.delete("Session", "idSession ="+_id, null);
    }

    /**
     * Remove utilisateur int.
     *
     * @param _id l'id
     * @return  int
     */
    public int removeUtilisateur(int _id)
    {
        return bdd.delete("Utilisateur", "idUtilisateur ="+_id, null);
    }

    //endregion

    //region Get Lists

    /**
     * Get activites list.
     *
     * @return la list des activites
     */
    public List<Activite> getActivites(){

        List<Activite> lesActivites = new ArrayList<>();

        Cursor c = bdd.rawQuery("SELECT * FROM Activite;", null);

        c.moveToFirst();
        while (!c.isAfterLast()){
            lesActivites.add(cursorToActivite(c));
            c.moveToNext();
        }
        c.close();

        return lesActivites;
    }

    /**
     * Get niveau douleurs list.
     *
     * @return la list des niveaux de douleur
     */
    public List<NiveauDouleur> getNiveauDouleurs(){
        List<NiveauDouleur> lesNiveauxDouleurs = new ArrayList<>();
        Cursor c = bdd.rawQuery("SELECT * FROM NiveauDouleur;", null);

        c.moveToFirst();
        while (!c.isAfterLast()){
            lesNiveauxDouleurs.add(cursorToNiveauDouleur(c));
            c.moveToNext();
        }

        c.close();
        return lesNiveauxDouleurs;
    }

    /**
     * Gets sessions.
     *
     * @return  l'ArrayList les sessions
     */
    public List<Session> getSessions()
    {
        List<Session> lesSessions = new ArrayList<>();
        Cursor c = bdd.rawQuery("SELECT * FROM Session,Activite WHERE Session.idActivite = Activite.idActivite;", null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            lesSessions.add(cursorToSession(c));
            c.moveToNext();
        }

        c.close();
        return lesSessions;
    }

    //endregion

    //region Get Object From Id

    /**
     * Gets activite.
     *
     * @param _id l'id
     * @return les activite
     */
    public Activite getActivite(int _id)
    {
        Cursor c = bdd.rawQuery("SELECT * FROM Activite WHERE idActivite ="+_id, null);
        c.moveToFirst();
        return cursorToActivite(c);
    }

    /**
     * Gets niveau douleur.
     *
     * @param _id l'id
     * @return le niveau douleur
     */
    public NiveauDouleur getNiveauDouleur(int _id)
    {
        Cursor c = bdd.rawQuery("SELECT * FROM NiveauDouleur WHERE idNiveauDouleur ="+ _id, null);

        return cursorToNiveauDouleur(c);
    }

    /**
     * Gets session.
     *
     * @param _id l'id
     * @return la session
     */
    public Session getSession(int _id)
    {
        Cursor c = bdd.rawQuery("SELECT * FROM Session, Activite WHERE Session.idActivite = idActivite AND idSession ="+_id, null);

        return cursorToSession(c);
    }

    /**
     * Gets utilisateur.
     *
     * @param _id l'id
     * @return l'utilisateur
     */
    public Utilisateur getUtilisateur(int _id)
    {
        Cursor c = bdd.rawQuery("SELECT * FROM Utilisateur WHERE idUtilisateur =" + _id, null);

        return cursorToUtilisateur(c);
    }

    //endregion

    // region get Object from Activité

    /**
     * Gets session activite.
     *
     * @param _activite l'activite
     * @return les session d'activite
     */
    public List<Session> getSessionActivite(Activite _activite)
    {
        List<Session> lesSessions = new ArrayList<>();
        Cursor c = bdd.rawQuery("SELECT idSession, dateDebut, dateFin, kmParcourus, niveauDouleur, Session.idActivite FROM Session, Activite WHERE Session.idActivite = Activite.idActivite AND Session.idActivite ="+_activite.getId(), null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            lesSessions.add(cursorToSession(c));
            c.moveToNext();
        }

        c.close();
        return lesSessions;
    }

    //endregion

    /**
     * Get nom utilisateur.
     *
     * @return l'utilisateur
     */
//region Get utilisateur
    public Utilisateur getNom(){
        Cursor c = bdd.rawQuery("SELECT nom FROM Utilisateur; ", null);
        return cursorToUtilisateur(c);
    }

    /**
     * Get prenom utilisateur.
     *
     * @return l'utilisateur
     */
    public Utilisateur getPrenom(){
        Cursor c = bdd.rawQuery("SELECT prenom FROM Utilisateur; ", null);
        return cursorToUtilisateur(c);
    }

    /**
     * Get poids utilisateur.
     *
     * @return l'utilisateur
     */
    public Utilisateur getPoids(){
        Cursor c = bdd.rawQuery("SELECT poids FROM Utilisateur; ", null);
        return cursorToUtilisateur(c);
    }

    /**
     * Afficher utilisateur list.
     *
     * @return la list
     */
    public List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> lesUtilisateurs = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().query( "Utilisateur",
                new String[] { "idUtilisateur", "nom", "prenom", "poids" },
                null, null, null, null, "idUtilisateur asc" );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Utilisateur utilisateur = null ;
            try {
                utilisateur = new Utilisateur( cursor.getInt( 0 ), cursor.getString( 1 ),
                        cursor.getString( 2 ),  cursor.getFloat( 3 ) );
            } catch (UtilisateurException e) {
                e.printStackTrace();
            }
            lesUtilisateurs.add( utilisateur );
            cursor.moveToNext();
        }
        cursor.close();

        return lesUtilisateurs;
    }

    //endregion

    //region Get Object From String

    /**
     * Gets activite from libelle.
     *
     * @param _libelle le libelle
     * @return Le libelle de l'activite
     */
    public Activite getActiviteFromLibelle(String _libelle)
    {
        Cursor c = bdd.rawQuery("SELECT * FROM Activite WHERE libelle LIKE '"+_libelle+"';", null);
        c.moveToFirst();

        return cursorToActivite(c);
    }

    /**
     * Gets niveau douleur from date.
     *
     * @param _date la date
     * @return  la date du niveau douleur
     */
    public NiveauDouleur getNiveauDouleurFromDate(String _date)
    {
        Cursor c = bdd.query("NiveauDouleur", new String[] {"idNiveauDouleur", "niveau", "date"}, "date LIKE \""+_date+"\"", null, null, null, null);

        return cursorToNiveauDouleur(c);
    }

    /**
     * Gets session from date.
     *
     * @param _date la date
     * @return la date de la session
     */
    public Session getSessionFromDate(String _date)
    {
        Cursor c = bdd.rawQuery("SELECT * FROM Session WHERE dateDebut LIKE '"+_date+"'", null);

        return cursorToSession(c);
    }

    /**
     * Gets utilisateur from name.
     *
     * @param _name le nom
     * @return Le name de l'utilisateur
     */
    public Utilisateur getUtilisateurFromName(String _name)
    {
        Cursor c = bdd.query("NiveauDouleur", new String[] {"idUtilisateur", "nom", "prenom"}, "nom LIKE \""+_name+"\"", null, null, null, null);

        return cursorToUtilisateur(c);
    }

    //endregion

    //region cursorToClass

    /**
     * Curseur pour Activité l'activité.
     *
     * @param c le cursor
     * @return l'activite
     */
    public Activite cursorToActivite(Cursor c)
    {
        if (c.getCount() == 0)
            return null;

        Activite activite = new Activite();
        activite.setId(c.getInt(0));
        activite.setLibelle(c.getString(1));
        activite.setImage(c.getString(2));

        return activite;
    }

    /**
     * Curseur du NiveauDouleur niveau douleur.
     *
     * @param c le cursor
     * @return le niveau douleur
     */
    public NiveauDouleur cursorToNiveauDouleur(Cursor c)
    {
        if (c.getCount() == 0)
            return null;


        NiveauDouleur niveauDouleur = new NiveauDouleur();
        niveauDouleur.setId(c.getInt(0));
        niveauDouleur.setNiveau(c.getInt(2));
        niveauDouleur.setDate(new Date(c.getLong(1)));
        niveauDouleur.setEmplacement(c.getString(3));

        return niveauDouleur;
    }

    /**
     * Cursor de la Session session.
     *
     * @param c le cursor
     * @return la session
     */
    public Session cursorToSession(Cursor c)
    {
        if (c.getCount() == 0)
            return null;

        Session s = new Session();

        s.setId(c.getInt(0));
        s.setDateDebut(new Date(c.getLong(1)));
        s.setDateFin(new Date(c.getLong(2)));
        s.setKmParcourus(c.getFloat(3));
        s.setNiveauDouleur(c.getInt(4));
        s.setActivite(getActivite(c.getInt(5)));

        return s;
    }

    /**
     * Cursor d'Utilisateur utilisateur.
     *
     * @param c le cursor
     * @return l'utilisateur
     */
    public Utilisateur cursorToUtilisateur(Cursor c)
    {
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(c.getInt(0));
        utilisateur.setNom(c.getString(1));
        utilisateur.setPrenom(c.getString(2));
        utilisateur.setPoids((c.getFloat(3)));

        return utilisateur;
    }

    //endregion
    //open
    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM  Activite";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }


    //endregion
}
