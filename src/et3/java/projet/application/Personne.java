package et3.java.projet.application;

import java.util.Date;

public class Personne extends AmateurDArbres{
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String adresse;

    public String getNom() {
    	return nom;
    }
    public String getPrenom() {
    	return prenom;
    }
 	public Date getDateNaissance() {
 		return dateDeNaissance;
 	}
 	public String getAdresse() {
 		return adresse;
 	}
 	
 	public void setNom(String nom) {
 		this.nom = nom;	
    }
    public void setPrenom(String prenom) {
    	this.prenom = prenom;
    }
 	public void setDateNaissance(Date date) {
 		this.dateDeNaissance = date;
 	}
 	public void setAdresse(String adresse) {
 		this.adresse = adresse;
 	}
 	
 
 	public void SupprimerDonnées() {
 		nom = "";
 		prenom = "";
 		dateDeNaissance = null;
 		adresse = "";
 	}
    
}
