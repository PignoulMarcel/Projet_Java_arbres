package et3.java.projet.application;

import java.util.Date;

public class Personne extends AmateurDArbres{
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String adresse;

    public Personne(String nom, String prenom, Date dateDeNaissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
    }

    protected Personne(String nom, String prenom, Date dateDeNaissance, String adresse,int id) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }
}
