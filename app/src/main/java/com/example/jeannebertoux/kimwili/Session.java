package com.example.jeannebertoux.kimwili;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * Le type Session.
 */
public class Session {
   //attributs privées de la classe Session
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private float kmParcourus;
    private int niveauDouleur;
    private Activite uneActivite;
    private ArrayList<Activite> lesActivite;

    /**
     * // Contrusteur par défaut de la classe Session
     */

    public Session()
    {

    }

    /**
     * Constructeur par Copie de la classe Session
     *
     * @param _session  session
     * @throws SessionException  session exception
     */
    public Session (Session _session) throws SessionException
    {
    	if (_session == null)
    	{
    		throw new SessionException();
    	}
    	else
    	{
    		id = _session.id;
            dateDebut = _session.dateDebut;
            dateFin = _session.dateFin;
            kmParcourus = _session.kmParcourus;
            niveauDouleur = _session.niveauDouleur;
            uneActivite = _session.uneActivite;
            lesActivite = new ArrayList<>();

    	}
    }

    /**
     * Constructeur de la classe Session
     *
     * @param unId             un id
     * @param uneDateDebut     une date debut
     * @param uneDateFin       une date fin
     * @param unKmParcouru     un km parcouru
     * @param unNiveauDouleur  un niveau douleur
     * @param activite         activite
     * @throws SessionException the session exception
     */
    public Session(int unId, Date uneDateDebut, Date uneDateFin, float unKmParcouru, int unNiveauDouleur, Activite activite) throws SessionException{
        if (uneDateDebut == null || uneDateFin == null || String.valueOf(unKmParcouru) == "" || String.valueOf(unNiveauDouleur) == "")
        {
        	throw new SessionException();
        }
        else
        {
        	this.id = unId;
            this.dateDebut = uneDateDebut;
            this.dateFin = uneDateFin;
            this.kmParcourus = unKmParcouru;
            this.niveauDouleur = unNiveauDouleur;
            this.uneActivite = activite;
        }
    }

    /**
     * getteur id de la classe Session
     *
     * @return id id
     */
    public int getId()
    {
        return id;
    }

    /**
     * setteur id de la classe Session
     *
     * @param _id the id
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * getteur dateDebut de la classe Session
     *
     * @return dateDebut date debut
     */
    public long getDateDebut()
    {
        return dateDebut.getTime();
    }

    /**
     * setteur dateDebut de la classe Session
     *
     * @param _dateDebut la date debut
     */
    public void setDateDebut(Date _dateDebut)
    {
        dateDebut = _dateDebut;
    }

    /**
     * getteur datFin de la classe Session
     *
     * @return dateFin date de fin
     */
    public long getDateFin()
    {
        return dateFin.getTime();
    }

    /**
     * setteur dateFin de la classe Session
     *
     * @param _datefin la date de fin
     */
    public void setDateFin(Date _datefin)
    {
        dateFin = _datefin;
    }

    /**
     * getteur kmParcourus de la classe Session
     *
     * @return kmParcourus km parcourus
     */
    public float getKmParcourus()
    {
        return kmParcourus;
    }

    /**
     * setteur kmParcourus de la classe Session
     *
     * @param _kmParcourus les km parcourus
     */
    public void setKmParcourus(float _kmParcourus)
    {
        this.kmParcourus = _kmParcourus;
    }

    /**
     * getteur niveauDouleur de la classe Session
     *
     * @return niveauDouleur niveau douleur
     */
    public int getNiveauDouleur()
    {
        return niveauDouleur;
    }

    /**
     * setteur niveauDouleur de la classe Session
     *
     * @param _niveauDouleur le niveau douleur
     */
    public void setNiveauDouleur(int _niveauDouleur)
    {
        this.niveauDouleur = _niveauDouleur;
    }

    /**
     * getteur Activite de la classe Session
     *
     * @return uneActivite activite
     */
    public Activite getActivite(){
        return this.uneActivite;
    }

    /**
     * setteur activite de la classe Session
     *
     * @param activite l'activite
     */
    public void setActivite(Activite activite){
        this.uneActivite = activite;
    }

    /**
     * redéfinition de la classe toString()
     * @return une chaine
     */
    @Override
    public String toString()
    {
        return "Vous avez parcouru " + kmParcourus + " km en " + getTempsSession() + " min";
    }

    /**
     * retourne la date début au format francais
     *
     * @return une chaine
     */
    public String toStringDateDebut(){

            return this.dateDebut.getDay() + "/" + this.dateDebut.getMonth() + "/" + this.dateDebut.getYear() + " " + this.dateDebut.getHours() + ":" + this.dateDebut.getMinutes();
    }

    /**
     * retourne la date fin au format francais
     *
     * @return une chaine
     */
    public String toStringDateFin(){

        return " au " +  this.dateFin.getDay() + "/" + this.dateFin.getMonth() + "/" + this.dateFin.getYear() ;
    }

    /**
     * redéfinition de la méthode equals
     * @param obj
     * @return un boolean
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session sess = (Session)obj;
        if (id == sess.id)
            return true;
        return false;
    }

    /**
     * fonction de recherche permettant de trouver un libelle de l'activite contenue dans la session
     *
     * @param unLibelle  un libelle
     * @return boolean boolean
     */
    public boolean rechercherUnLibelleActivite( String unLibelle){
        int i=0;
        boolean trouver;

        while (i< this.lesActivite.size() && !unLibelle.equals(lesActivite.get(i).getLibelle())){
            i = i+1;
        }
        if(i<this.lesActivite.size()){
            trouver = true;
        }
        else{
            trouver = false;
        }
        return trouver;
    }

    /**
     * Recupère le temps d'une session en minutes
     *
     * @return timeUnit.convert(diff, TimeUnit.MILLISECONDS) temps session
     */
    public long getTempsSession()
    {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        long diff = dateFin.getTime() - dateDebut.getTime();
        return timeUnit.convert(diff,TimeUnit.MILLISECONDS);
    }

    public String getDateDebutToString(){
        TimeUnit timeUnit = TimeUnit.MINUTES;
        return String.valueOf(timeUnit.convert(dateDebut.getTime(), TimeUnit.MILLISECONDS));
    }
}
