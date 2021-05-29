package et3.java.projet.application;

import java.util.ArrayList;
import java.util.List;

import et3.java.projet.application.Membre;
import et3.java.projet.application.Operation;
//import et3.java.projet.application.Demande;


public class Association extends Entite {
	Membre president;

	List<Membre> membres = new ArrayList<Membre>();
	List<AmateurDArbres> donateurs = new ArrayList<AmateurDArbres>();

	
	//Renvoi le memebre de l'association
	//@param membre est le membre � renvoyer
	public void renvoiMembre(Membre membre){
		for(int i = 0; i<membres.size();i++) {
    		if(membres.get(i).getId() == membre.getId()) {
    			try {
    				//TODO
    				//Supprimer ici les donn�es li� au membre
    				
    				membres.remove(i);
    			}catch (IndexOutOfBoundsException e) {
    				System.out.println(e.getMessage());
    			}
    		}
    	}
    }
	
	public Membre GetPresident() {
		return president;
	}
	public void SetPresident(Membre president) {
		this.president = president;
	}

    public Operation genererRapport(){
    	Operation operation = new Operation();
    	
    	return operation;
    }
    
    //Met � zero le rapport d'activit� de l'exercice �coul�
    //Appel� lors d'une nouvelle ann�e
    public void ResetRapport() {	
    	
    }

   // public Demande envoiDemande(){

  //  }

    public void inscription(Personne personne){
    	//membres.add(personne);
    }

    public void renvoi(Membre membre){

    }

    public void payer(Membre membre){

    }

    //Ajoute l'amateur � la liste des amateurs d'arbre
    public void ajouterDonateur(AmateurDArbres amateurDArbres){
    	donateurs.add(amateurDArbres);
    }

    
    //Supprime le donateur sp�cifi�
    //@param amateurDArbres l'amateur qui doit etre suppprim� de la liste
    public void supprimerDonateur(AmateurDArbres amateurDArbres){
    	for(int i = 0; i<donateurs.size();i++) {
    		if(donateurs.get(i).getId() == amateurDArbres.getId()) {
    			try {
    				donateurs.remove(i);
    			}catch (IndexOutOfBoundsException e) {
    				System.out.println(e.getMessage());
    			}
    		}
    	}
    }


	
}
