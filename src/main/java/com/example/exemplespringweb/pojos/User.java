package com.example.exemplespringweb.pojos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Le nom est requis")
    private String nom;
    @NotBlank(message = "Le prenom est requis")
    private String prenom;
    @NotBlank(message = "Le pseudo est requis")
    @Size(min = 4, message = "Le pseudo doit contenir au moins 4 caractères")
    private String pseudo;

    public User(String nom, String prenom, String pseudo) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
