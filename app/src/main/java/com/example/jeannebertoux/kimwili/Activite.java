package com.example.jeannebertoux.kimwili;

import java.io.Serializable;

/**
 *@author Jeanne Bertoux et Kilian Corbel
 */
public class Activite implements Serializable{
    // attributs privées
    private int id;
    private String libelle;
    private String image;

    /**
     * Constructeur par défaut de la classe Activite
     */
// Constructeur par défaut
    public Activite() {

    }

    /**
     * Constructeur par copie
     *
     * @param _activite l'activite
     * @throws ActiviteException l'activite exception
     */
    public Activite(Activite _activite) throws ActiviteException {
        if (_activite == null) {
            throw new ActiviteException();
        } else {
            id = _activite.id;
            libelle = _activite.libelle;
            image = _activite.image;
        }
    }

    // Constructeur

    /**
     * Constructeur
     *
     * @param unId       un id
     * @param unLibelle  un libelle
     * @param _image     image
     * @throws ActiviteException l'activite exception
     */
    public Activite(int unId, String unLibelle, String _image) throws ActiviteException {
        if (String.valueOf(unId) == "" || unLibelle == "") {
            throw new ActiviteException();
        } else {
            this.id = unId;
            this.libelle = unLibelle;
            this.image = _image;
        }
    }

    /**
     * getteur id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setteur id
     *
     * @param _id l'id
     */
    public void setId(int _id) {
        id = _id;
    }

    /**
     * getteur libelle
     *
     * @return  libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * setteur libelle
     *
     * @param _libelle le libelle
     */
    public void setLibelle(String _libelle) {
        libelle = _libelle;
    }

    /**
     * getteur Image
     *
     * @return image string
     */
    public String getImage(){
        return image;
    }

    /**
     * setteur image
     *
     * @param _image l' image
     */
    public void setImage(String _image){
        image = _image;
    }

    /**
     * redéfinition de la méthode toString()
     * @return une chaine
     */
    public String toString() {
        return "L'activité " + libelle + " porte l'identifiant " + id;
    }

    /**
     * redéfinition de la méthode equals
     * @param obj
     * @return un booleen
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Activite act = (Activite) obj;
        if (id == act.id)
            return true;
        return false;
    }


}
