package et3.java.projet.application;


import java.util.*;


public class Association extends Entite {
	Membre president;
	ArrayList<Visite> listeVisites;
	ArrayList<Operation> operations;
	StringBuilder rapport;
	ArrayList<Membre> membres;
	ArrayList<AmateurDArbres> donateurs;

	public Association(String nomEntreprise) {
		super(nomEntreprise);
		this.president = null;
		this.listeVisites = new ArrayList<Visite>();
		this.operations = new ArrayList<Operation>();
		this.rapport = new StringBuilder();
		this.membres = new ArrayList<Membre>();
		this.donateurs = new ArrayList<AmateurDArbres>();
		
		
	}
	public Membre ChercheMembre(Personne personne) {
		for(int i = 0; i<membres.size(); i++) {
			if(personne.getNom()==membres.get(i).getNom() && personne.getPrenom()==membres.get(i).getPrenom()) {
				return membres.get(i);
			}
		}
		return null;
	}
	
	//Renvoi le memebre de l'association et supprime ses donn�es
	//@param membre est le membre � renvoyer
	public void departMembre(Membre membre){
					if(!membres.contains(membre)){
						rapport.append(new Date().toString() + "- " + "Le membre " + membre.getNom() + " " + membre.getPrenom() + " n'a pas pu �tre exclu car il n'a pas �t� trouv�\n");

					}else if(this.president.getId()==membre.getId()){
						rapport.append(new Date().toString() + "- " + "Le membre " + membre.getNom() + " " + membre.getPrenom() + "n'a pas pu �tre exclu car il �tait pr�sident\n");

    				}else {
						membres.remove(membre);
						//On ne sait pas s'il redevient amateurDArbre (on considere que non)
						rapport.append(new Date().toString() + "- " + "Le membre " + membre.getNom() + " " + membre.getPrenom() + " ne fait plus parti de l'assocation. L'ensemble de ses donn�es personnelles ont �t� supprim�es.\n");
					}
	}
	
	public Membre GetPresident() {
		return president;
	}

	public void setPresident(Membre president) {
		if(this.president == null){
			this.president = president;
			rapport.append(new Date().toString() + "- " + president.getNom() + " " + president.getPrenom() + " a �t� �lu pr�sident\n");
		}else if(this.president.getId()!=president.getId()){
			this.president = president;
			rapport.append(new Date().toString() + "- " + president.getNom() + " " + president.getPrenom() + " a �t� �lu pr�sident\n");
		}

	}

    public void genererRapport(){
    	System.out.println(rapport);
    }
    
    //Met � zero le rapport d'activit� de l'exercice �coul�
    public void ResetRapport() {	
		rapport = new StringBuilder();
    }

    public boolean inscription(Personne personne){
		Membre nouveauMembre = new Membre(personne, new Date());
		for(Membre mem : membres){
			if(mem.getId()==nouveauMembre.getId())return false;
		}
		membres.add(nouveauMembre);
		if(membres.size()==1) setPresident(nouveauMembre);
		rapport.append(new Date().toString() + "- " +nouveauMembre.getNom() + " " + nouveauMembre.getPrenom()+" a rejoint l'association\n");
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
    				departMembre(membres.get(i));
    				NbMembreRenvoye +=1;
    			}catch (IndexOutOfBoundsException e) {
    				System.out.println(e.getMessage());
    			}
    		}
    	}
    	if(NbMembreRenvoye == 1) {
			rapport.append(new Date().toString() + "- " +"1 personne n'a pas r�gl� sa cotisation");
    	}else if(NbMembreRenvoye>1) {
			rapport.append(new Date().toString() + "- " +NbMembreRenvoye + " personnes n'ont pas r�gl� leur cotisation");
    	}else {
			rapport.append(new Date().toString() + "- " +"Tous les membres ont r�gl� leur cotisation");
    	}
    }


    public boolean payerFacture(float montant){
    	for(Membre membre:membres){
			if(Math.random()>0.95 && membre.getFonds()>=montant){
				membre.changeFonds(-montant);
				this.changeFonds(montant);
				Operation operation = new Operation(membre,this, montant);
				operations.add(operation);
				membre.ajoutCotisation(operation);
				rapport.append(operation.getDate().toString() + "- "  + membre.getNom() + " " + membre.getPrenom() + " a pay� " + montant + "� \n");
			}
		}
    	return false;
    }

    public void demandeDons(){
		for(AmateurDArbres donateur : donateurs){
			if(Math.random()>0.5 && donateur.getFonds()>=1.0f){
				float montant = (float) (Math.floor(100 * (Math.random() * donateur.getFonds()))/100.0f);
				donateur.changeFonds(-montant);
				this.changeFonds(montant);
				operations.add(new Operation(donateur,this, montant));
				rapport.append(new Date().toString() + "- Un donateur a donn� " + montant + "� \n");
			}
		}
	}

    //Ajoute l'amateur � la liste des amateurs d'arbre
    public boolean ajouterDonateur(AmateurDArbres amateurDArbres){
		for(AmateurDArbres donateur : donateurs){
			if(donateur.getId()==amateurDArbres.getId())return false;
		}
		donateurs.add(amateurDArbres);
		rapport.append(new Date().toString() + "- L'association a un nouveau donateur \n");
		return true;
    }
    
    //Supprime le donateur sp�cifi�
    //@param amateurDArbres l'amateur qui doit etre supprim� de la liste
    public void supprimerDonateur(AmateurDArbres amateurDArbres){
		if(!donateurs.contains(amateurDArbres)){
			rapport.append(new Date().toString() + "- Le donnateur n'a pas pu �tre trouv�\n");
		}else {
			donateurs.remove(amateurDArbres);
			rapport.append(new Date().toString() + "- L'association a perdu un donateur. L'ensemble de ses donn�es personnelles ont �t� supprim�es.\n");
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

		Vote voteFinal = new Vote((ArrayList<Arbre>)uniquesArbres);
		rapport.append(new Date().toString() + "- Les votes de l'association sont : \n"
				+"  1. " + voteFinal.getListeArbre().get(0).getNom()
				+"  2. " + voteFinal.getListeArbre().get(1).getNom()
				+"  3. " + voteFinal.getListeArbre().get(2).getNom()
				+"  4. " + voteFinal.getListeArbre().get(3).getNom()
				+"  5. " + voteFinal.getListeArbre().get(4).getNom() + "\n");

		return voteFinal;

	}

	public void genererVisites(ArrayList<Arbre> listeArbres){
		for(Membre membre : membres){
			if(Math.random()>0.4){
				float co�t = (float)Math.floor(Math.random() * 50 * 100)/100.0f;
				Visite visite = new Visite(membre,listeArbres.get((int)(Math.random() * (listeArbres.size()))), co�t);
				boolean occup�e = false;
				for(Visite vis : listeVisites){
					if(vis.getArbre() == visite.getArbre())occup�e=true;
				}
				if(!occup�e && visite.getCout() < this.getFonds()){
					listeVisites.add(visite);
					payer(membre, co�t);
					rapport.append(new Date().toString() + "- Une visite pour " + visite.getArbre().getNom() +" a �t� programm�e\n");
				}
			}
		}
	}

	public void payer(Membre membre, float montant){
		membre.changeFonds(montant);
		this.changeFonds(-montant);
		Operation operation = new Operation(this,membre, montant);
		operations.add(operation);
		rapport.append(operation.getDate().toString() + "- "  + membre.getNom() + " " + membre.getPrenom() + " a �t� pay� " + montant + ".\n");
	}



}
