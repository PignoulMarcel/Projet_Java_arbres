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

    public Personne(Membre membre){
        super(membre.getId());
        this.nom = membre.getNom();
        this.prenom = membre.getPrenom();
        this.dateDeNaissance = membre.getDateDeNaissance();
        this.adresse = membre.getAdresse();
        membre = null;

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
