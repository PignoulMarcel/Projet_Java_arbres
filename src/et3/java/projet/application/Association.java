package et3.java.projet.application;


import java.time.Year;
import java.util.*;

import et3.java.projet.application.Operation;

import et3.java.projet.application.Membre;
import et3.java.projet.application.Operation;
//import et3.java.projet.application.Demande;


public class Association extends Entite {
	Membre president;
	ArrayList<Visite> listeVisites;
	ArrayList<Operation> listeOperations;
	ArrayList<Membre> membres;
	ArrayList<AmateurDArbres> donateurs;

	
	//Renvoi le memebre de l'association et supprime ses donn�es
	//@param membre est le membre � renvoyer
	public void renvoiMembre(Membre membre){
					if(!membres.contains(membre)){
						System.out.println("Le membre " + membre.getNom() + " " + membre.getPrenom() + " n'a pas pu �tre trouv�");

					}else if(this.president.getId()==membre.getId()){
    					System.out.println("Vous ne pouvez pas renvoyer le president");

    				}else {
						membres.remove(membre);
						//On ne sait pas s'il redevient amateurDArbre (on considere que non)
						System.out.println("Le membre " + membre.getNom() + " " + membre.getPrenom() + " ne fait plus parti de l'assocation. L'ensemble de ses donn�es personnelles ont �t� supprim�es.\n");
					}
	}

	
	public Membre ChercheMembre(Personne personne) {
		//TODO
		return null;
	}
	
	public Membre GetPresident() {
		return president;
	}

	public void SetPresident(Membre president) {
		if(this.president!=null && this.president.getId()!=president.getId())this.president = president;
	}

    public Operation genererRapport(){
    	Operation operation = new Operation();
    	return operation;
    }
    
    //Met � zero le rapport d'activit� de l'exercice �coul�
    //Appel� lors d'une nouvelle ann�e
    public void ResetRapport() {	

    }

    public Demande envoiDemande(){

    }

    public boolean inscription(Personne personne){
		Membre nouveauMembre = new Membre(personne, new Date());
		for(Membre mem : membres){
			if(mem.getId()==nouveauMembre.getId())return false;
		}
		membres.add(nouveauMembre);
		return true;
    }


    //V�rifie si chaque membre a pay� ses cotisations durant l'ann�e �coul�
    //Si pas le cas, le supprime de la liste des membres
    public void CotisationNonR�gl�() {
    	int NbMembreRenvoye = 0;
    	for(int i = 0; i<membres.size();i++) {
			boolean pay� = false;
			ArrayList<Operation> cotisations = membres.get(i).getCotisations();
			for(Operation operation : cotisations){
				if(operation.getCrediteur() == this)pay�=true;
			}

    		if(!pay�) {
    			try {
    				renvoiMembre(membres.get(i));
    				NbMembreRenvoye +=1;
    			}catch (IndexOutOfBoundsException e) {
    				System.out.println(e.getMessage());
    			}
    		}
    	}
    	if(NbMembreRenvoye == 1) {
    		System.out.println("1 personne n'a pas r�gl� sa cotisation");
    	}else if(NbMembreRenvoye>1) {
    		System.out.println(NbMembreRenvoye + " personnes n'ont pas r�gl� leur cotisation");
    	}else {
    		System.out.println("Tous les membres ont r�gl� leur cotisation");
    	}
    }
    //TODO
    public boolean payerFacture(float montant){
    	float montantTotal = 0f;				//Montant que poss�de l'association
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

    //Ajoute l'amateur � la liste des amateurs d'arbre
    public boolean ajouterDonateur(AmateurDArbres amateurDArbres){
		for(AmateurDArbres donateur : donateurs){
			if(donateur.getId()==amateurDArbres.getId())return false;
		}
		donateurs.add(amateurDArbres);
		return true;
    }
    
    //Supprime le donateur sp�cifi�
    //@param amateurDArbres l'amateur qui doit etre supprim� de la liste
    public void supprimerDonateur(AmateurDArbres amateurDArbres){
		if(!donateurs.contains(amateurDArbres)){
			System.out.println("Le donnateur n'a pas pu �tre trouv�");
		}else {
			donateurs.remove(amateurDArbres);
			System.out.println("Le donateur ne donne plus � l'assocation. L'ensemble de ses donn�es personnelles ont �t� supprim�es.\n");
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
