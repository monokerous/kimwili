package com.example.jeannebertoux.kimwili;


/**
 *  @author Jeanne Bertoux et Kilian Corbel
 */
public class Utilisateur {
    // attributs privés
    private int id;
    private String nom;
    private String prenom;
    private float poids;

    /**
     * Constructeur Par defaut
     */
    public Utilisateur()
    {

    }

    /**
     * Constructeur par copie
     *
     * @param _utilsateur l' utilsateur
     * @throws UtilisateurException l' utilisateur exception
     */
    public Utilisateur(Utilisateur _utilsateur) throws UtilisateurException{
    	if (_utilsateur == null)
    	{
    		throw new UtilisateurException();
    	}
    	else
    	{
    		id = _utilsateur.id;
            nom = _utilsateur.nom;
            prenom = _utilsateur.prenom;
            poids = _utilsateur.poids;
    	}
    }

    /**
     * Constructeur de la classe Utilisateur
     *
     * @param unId      un id
     * @param unNom     un nom
     * @param unPrenom  un prenom
     * @param unPoids   un poids
     * @throws UtilisateurException the utilisateur exception
     */
    public Utilisateur(int unId, String unNom, String unPrenom, float unPoids) throws UtilisateurException{
    	if (String.valueOf(unId) == "" || unNom == "" || unPrenom == "" || String.valueOf(unPoids) == "")
    	{
    		throw new UtilisateurException();
    	}
    	else
    	{
    		this.id = unId;
            this.nom = unNom;
            this.prenom = unPrenom;
            this.poids = unPoids;
    	}
    }

    /**
     * getteur id de la classe Utilisateur
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * setteur id de la classe Utilisateur
     *
     * @param _id  l'id
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * getteur nom de la classe Utilisateur
     *
     * @return nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * setteur nom de la classe Utilisateur
     *
     * @param _nom le nom
     */
    public void setNom(String _nom)
    {
        nom = _nom;
    }

    /**
     * getteur prenom de la classe Utilisateur
     *
     * @return prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * setteur prenom de la classe Utilisateur
     *
     * @param _prenom le prenom
     */
    public void setPrenom(String _prenom)
    {
        prenom = _prenom;
    }

    /**
     * getteur poids de la classe Utilisateur
     *
     * @return poids poids
     */
    public float getPoids()
    {
        return poids;
    }

    /**
     * setteur poid de la la classe Utilisateur
     *
     * @param _poids le poids
     */
    public void setPoids(float _poids)
    {
        poids = _poids;
    }

    /**
     * redéfinition de la méthode toString()
     *
     * @return une chaine
     */
    public String toString()
    {
        return "L'utilisateur " + prenom + " " + nom + " porte l'identifiant " + id;
    }

    /**
     * redéfinition de la méthode equals()
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utilisateur user = (Utilisateur)obj;
        if (id == user.id)
            return true;
        return false;
    }
}
