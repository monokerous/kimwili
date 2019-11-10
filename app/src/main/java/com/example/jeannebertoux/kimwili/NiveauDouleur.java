package com.example.jeannebertoux.kimwili;

import java.util.Date;

/**
 * @author Jeanne Bertoux et Kilian Corbel
 * The type Niveau douleur.
 */
public class NiveauDouleur {
    // attributs privées
    private int id;
    private Date date;
    private int niveau;
    private String emplacement;

    /**
     * Constructeur par défaut de la classe NiveauDouleur
     */
// Constructeur par défaut
    public NiveauDouleur()
    {

    }

    /**
     * Constructeur par copie de la classe NiveauDouleur
     *
     * @param _niveauDouleur le niveau douleur
     * @throws NiveauDouleurException le niveau douleur exception
     */
    public NiveauDouleur(NiveauDouleur _niveauDouleur) throws NiveauDouleurException
    {
    	if (_niveauDouleur == null)
    	{
    		throw new NiveauDouleurException();
    	}
    	else
    	{
    		id = _niveauDouleur.id;
            date = _niveauDouleur.date;
            niveau = _niveauDouleur.niveau;
            emplacement = _niveauDouleur.emplacement;
    	}
    }

    // Constructeur

    /**
     * Constructeur NiveauDouleur
     *
     * @param unId          un id
     * @param uneDate       une date
     * @param unNiveau      un niveau
     * @param _emplacement  l'emplacement
     * @throws NiveauDouleurException le niveau douleur exception
     */
    public NiveauDouleur( int unId, Date uneDate, int unNiveau, String _emplacement) throws NiveauDouleurException
    {
    	if (String.valueOf(unId) == "" || uneDate == null || String.valueOf(unNiveau) == "" || _emplacement == "")
    	{
    		throw new NiveauDouleurException();
    	}
    	else
    	{
    		this.id = unId;
            this.date = uneDate;
            this.niveau = unNiveau;
            emplacement = _emplacement;
    	}

    }

    /**
     * getteur id de la classe NiveauDouleur
     *
     * @return id id
     */
    public int getId()
    {
        return id;
    }

    /**
     * setteur id de la classe NiveauDouleur
     *
     * @param _id the id
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * getteurDate de la classe NiveauDouleur
     *
     * @return date date
     */
    public long getDate()
    {
        return date.getTime();
    }

    /**
     * setteur Date de la classe NiveauDouleur
     *
     * @param _date the date
     */
    public void setDate(Date _date)
    {
        date = _date;
    }

    /**
     * getteur niveau de la classe NiveauDouleur
     *
     * @return niveau niveau
     */
    public int getNiveau()
    {
        return niveau;
    }

    /**
     * setteur niveau de la classe NiveauDouleur
     *
     * @param _niveau the niveau
     */
    public void setNiveau(int _niveau)
    {
        niveau = _niveau;
    }

    /**
     * redéfinition de la méthode toString()
     *
     * @return une chaine
     */
    public String getEmplacement()
    {
        return emplacement;
    }

    /**
     * Sets emplacement.
     *
     * @param _emplacement the emplacement
     */
    public void setEmplacement(String _emplacement)
    {
        emplacement = _emplacement;
    }

    public String toString()
    {
        return "Au " + date.getDay() + "/" + date.getMonth() + "/" + date.getYear() + " le niveau de douleur à l'endroit : " + emplacement +" est de " + niveau;
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
        NiveauDouleur douleur = (NiveauDouleur)obj;
        if (id == douleur.id)
            return true;
        return false;
    }

}
