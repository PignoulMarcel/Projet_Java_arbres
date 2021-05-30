package et3.java.projet.application;


import java.time.Year;
import java.util.*;

import et3.java.projet.application.Operation;

import et3.java.projet.application.Membre;
import et3.java.projet.application.Operation;
//import et3.java.projet.application.Demande;


public class Association extends Entite {
	Membre president;

	List<Membre> membres = new ArrayList<Membre>();
	List<AmateurDArbres> donateurs = new ArrayList<AmateurDArbres>();

	
	//Renvoi le memebre de l'association et supprime ses données
	//@param membre est le membre à renvoyer
	public void renvoiMembre(Membre membre){
		for(int i = 0; i<membres.size();i++) {
    		if(membres.get(i).getId() == membre.getId()) {
    			try {
    				//TODO
    				//Supprimer ici les données lié au membre
    				String nom = membres.get(i).getNom();
    				String prenom = membres.get(i).getPrenom();


    				membres.remove(i);
    				System.out.println("Le membre " + nom + " " + prenom + " ne fait plus parti de l'assocation. L'ensemble de ses données personnelles ont été supprimées.\n");
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
    
    //Met à zero le rapport d'activité de l'exercice écoulé
    //Appelé lors d'une nouvelle année
    public void ResetRapport() {	
    	
    }

    public Demande envoiDemande(){

    }

    public void inscription(Personne personne){
    	//Operation.
    	//membres.add(personne);
    }

    //Vérifie si chaque membre a payé ses cotisations durant l'année écoulé
    //Si pas le cas, le supprime de la liste des membres
    public void CotisationNonRéglé() {
    	int NbMembreRenvoye = 0;
    	@SuppressWarnings("deprecation")
		Date UneAnnée = new Date(1,0,0);
    	for(int i = 0; i<membres.size();i++) {
    		
    		if((System.currentTimeMillis() - membres.get(i).getDerniereOperation().getDate().getTime()) > UneAnnée.getTime() ) {
    			try {
    				renvoiMembre(membres.get(i));
    				NbMembreRenvoye +=1;
    			}catch (IndexOutOfBoundsException e) {
    				System.out.println(e.getMessage());
    			}
    		}
    	}
    	if(NbMembreRenvoye == 1) {
    		System.out.println("1 personne n'a pas réglé sa cotisation");
    	}else if(NbMembreRenvoye>1) {
    		System.out.println(NbMembreRenvoye + " personnes n'ont pas réglé leur cotisation");
    	}else {
    		System.out.println("Tous les membres ont réglé leur cotisation");
    	}
    }
    
    public boolean payerFacture(float montant){
    	float montantTotal = 0f;				//Montant que possède l'association
    	for(int i = 0; i<membres.size(); i++) {
    		montantTotal += membres.get(i).getFonds();
    		if(montantTotal>montant) {
    			for(int j = 0; j<membres.size(); j++) {
    				if(membres.get(j).getFonds()>= montant){
    					membres.get(j).setFonds(membres.get(j).getFonds()-montant);
    					return true;
    				}else {
    					montant -= membres.get(j).getFonds();
    					membres.get(j).setFonds(0);
    				}
    			}
    		}
    	}

    	for(int i = 0; i<donateurs.size(); i++) {
    		montantTotal += donateurs.get(i).getFonds();
    		if(montantTotal>montant) {
    			for(int j = 0; j<donateurs.size(); j++) {
    				if(donateurs.get(j).getFonds()>= montant){
    					donateurs.get(j).setFonds(membres.get(j).getFonds()-montant);
    					return true;
    				}else {
    					montant -= donateurs.get(j).getFonds();
    					donateurs.get(j).setFonds(0);
    				}
    			}
    		}
    	}
    	return false;
    }


    public void payer(Membre membre){

    }

    //Ajoute l'amateur à la liste des amateurs d'arbre
    public void ajouterDonateur(AmateurDArbres amateurDArbres){
    	donateurs.add(amateurDArbres);
    }

    
    //Supprime le donateur spécifié
    //@param amateurDArbres l'amateur qui doit etre suppprimé de la liste
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


	public Vote genererVotes(ArrayList<Arbre> listeArbres){
		ArrayList<Arbre> nonRemarquables = new ArrayList<Arbre>();
		for(Arbre arbre : listeArbres){
			if(!arbre.getRemarquable())nonRemarquables.add(arbre);
		}

		ArrayList<Arbre> listeVotes = new ArrayList<Arbre>();
		for(Membre membre : membres){
			listeVotes.addAll(membre.vote(nonRemarquables).getListeArbre());
		}

		Set<Arbre> uniquesArbres = new HashSet<Arbre>(listeVotes);
		Collections.sort((ArrayList<Arbre>)uniquesArbres, new Comparator<Arbre>() {//tres lent, a refaire
			@Override
			public int compare(Arbre a1, Arbre a2) {
				boolean plusGrand=false;
				int frqA1 = Collections.frequency(listeVotes,a1);
				int frqA2 = Collections.frequency(listeVotes,a2);
				if(frqA1>frqA2){
					return 1;
				}else if(frqA1<frqA2){
					return 0;
				}

				if(a1.getCirconference()>a2.getCirconference()){
					return 1;
				}else if(a1.getCirconference()<a2.getCirconference()){
					return 0;
				}

				if(a1.getHauteur()>a2.getHauteur()){
					return 1;
				}else if(a1.getHauteur()<a2.getHauteur()){
					return 0;
				}

				return 1;
			}
		});

		return new Vote((ArrayList<Arbre>)uniquesArbres);

	}

}
