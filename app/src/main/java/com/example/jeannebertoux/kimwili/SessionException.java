package com.example.jeannebertoux.kimwili;

/**
 *  @author Jeanne Bertoux et Kilian Corbel
 */
public class SessionException extends Exception {
    /**
     * Constructeur de l' exception de Session.
     */
    public SessionException()
    {
        System.out.println("Erreur lors de la création de la session.");
    }
}
