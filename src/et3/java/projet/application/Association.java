package et3.java.projet.application;


import java.util.*;


public class Association extends Entite {
	Membre president;
	ArrayList<Visite> listeVisites;
	ArrayList<Operation> operations;
	StringBuilder rapport;
	ArrayList<Membre> membres;
	ArrayList<AmateurDArbres> donateurs;

	/**
	 * *Créé une entreprise
	 * @param nomEntreprise nom de l'entreprise
	 */
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

	/**
	 * *Enleve un membre de l'association
	 * @param membre membre à enlever
	 */
	public void departMembre(Membre membre){
					if(!membres.contains(membre)){
						rapport.append(new Date().toString() + "- " + "Le membre " + membre.getNom() + " " + membre.getPrenom() + " n'a pas pu être exclu car il n'a pas été trouvé.\n");

					}else if(this.president.getId()==membre.getId()){
						rapport.append(new Date().toString() + "- " + "Le membre " + membre.getNom() + " " + membre.getPrenom() + " n'a pas pu être exclu car il était président.\n");

    				}else {
						membres.remove(membre);
						//On ne sait pas s'il redevient amateurDArbre (on considere que non)
						rapport.append(new Date().toString() + "- " + "Le membre " + membre.getNom() + " " + membre.getPrenom() + " ne fait plus parti de l'assocation. L'ensemble de ses données personnelles ont été supprimées.\n");
					}
	}

	/**
	 * Donne le membre président
	 * @return membre président
	 */
	public Membre GetPresident() {
		return president;
	}

	/**
	 * Change de président
	 * @param president membre qui devient président
	 */
	public void setPresident(Membre president) {
		if(this.president == null){
			this.president = president;
			rapport.append(new Date().toString() + "- " + president.getNom() + " " + president.getPrenom() + " a été élu président.\n");
		}else if(this.president.getId()!=president.getId()){
			this.president = president;
			rapport.append(new Date().toString() + "- " + president.getNom() + " " + president.getPrenom() + " a été élu président.\n");
		}

	}

	/**
	 * Affiche le rapport d'activités
	 */
    public void genererRapport(){
    	System.out.println(rapport);
    }

	/**
	 * Efface le rapport d'activités
	 */
	public void ResetRapport() {
		rapport = new StringBuilder();
    }

	/**
	 * Inscrit une personne en tant que membre
	 * @param personne personne à inscrire
	 * @return vrai si la personne a été inscrite
	 */
	public boolean inscription(Personne personne){
		Membre nouveauMembre = new Membre(personne, new Date());
		for(Membre mem : membres){
			if(mem.getId()==nouveauMembre.getId())return false;
		}
		membres.add(nouveauMembre);
		if(membres.size()==1) setPresident(nouveauMembre);
		rapport.append(new Date().toString() + "- " +nouveauMembre.getNom() + " " + nouveauMembre.getPrenom()+" a rejoint l'association.\n");
		return true;
    }

	/**
	 * Verifie si les membres ont réglé leur cotisation, les renvoi sinon
	 */
	public void CotisationNonRéglé() {
    	int NbMembreRenvoye = 0;
    	for(int i = 0; i<membres.size();i++) {
			boolean payé = false;
			ArrayList<Operation> cotisations = membres.get(i).getCotisations();
			for(Operation operation : cotisations){
				if(operation.getCrediteur() == this)payé=true;
			}

    		if(!payé) {
    			try {
    				departMembre(membres.get(i));
    				NbMembreRenvoye += 1;
    			}catch (IndexOutOfBoundsException e) {
    				System.out.println(e.getMessage());
    			}
    		}
    	}
    	if(NbMembreRenvoye == 1) {
			rapport.append(new Date().toString() + "- 1 personne n'a pas réglé sa cotisation.\n");
    	}else if(NbMembreRenvoye>1) {
			rapport.append(new Date().toString() + "- " +NbMembreRenvoye + " personnes n'ont pas réglé leur cotisation.\n");
    	}else {
			rapport.append(new Date().toString() + "- Tous les membres ont réglé leur cotisation.\n");
    	}
    }


	/**
	 * Demande à tous les membres de payer
	 * @param montant montant à payer
	 */
    public void payerFacture(float montant){
    	for(Membre membre:membres){
			if(Math.random()>0.95 && membre.getFonds()>=montant){
				membre.changeFonds(-montant);
				this.changeFonds(montant);
				Operation operation = new Operation(membre,this, montant);
				operations.add(operation);
				membre.ajoutCotisation(operation);
				rapport.append(operation.getDate().toString() + "- "  + membre.getNom() + " " + membre.getPrenom() + " a payé " + montant + "€.\n");
			}
		}
    }

	/**
	 * Demande à tous les donateurs de donner s'il le souhaitent
	 */
	public void demandeDons(){
		for(AmateurDArbres donateur : donateurs){
			if(Math.random()>0.5 && donateur.getFonds()>=1.0f){
				float montant = (float) (Math.floor(100 * (Math.random() * donateur.getFonds()))/100.0f);
				donateur.changeFonds(-montant);
				this.changeFonds(montant);
				operations.add(new Operation(donateur,this, montant));
				rapport.append(new Date().toString() + "- Un donateur a donné " + montant + "€.\n");
			}
		}
	}

	/**
	 * Ajoute un donateur à l'association
	 * @param amateurDArbres donateur à ajouter
	 * @return vrai si le donateur a bien été ajouté
	 */
	public boolean ajouterDonateur(AmateurDArbres amateurDArbres){
		for(AmateurDArbres donateur : donateurs){
			if(donateur.getId()==amateurDArbres.getId())return false;
		}
		donateurs.add(amateurDArbres);
		rapport.append(new Date().toString() + "- L'association a un nouveau donateur.\n");
		return true;
    }

	/**
	 * Supprime un donateur de la liste
	 * @param amateurDArbres donateur à supprimer
	 */
    public void supprimerDonateur(AmateurDArbres amateurDArbres){
		if(!donateurs.contains(amateurDArbres)){
			rapport.append(new Date().toString() + "- Le donnateur n'a pas pu être trouvé.\n");
		}else {
			donateurs.remove(amateurDArbres);
			rapport.append(new Date().toString() + "- L'association a perdu un donateur. L'ensemble de ses données personnelles ont été supprimées.\n");
		}
    }

	/**
	 * Génère le vote de tous les membres
	 * @param listeArbres Liste des arbres sur lesquels voter
	 * @return Vote de l'association
	 */
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

	/**
	 * Génère la visite d'arbres par les membres
	 * @param listeArbres Arbres pouvant être visités
	 */
	public void genererVisites(ArrayList<Arbre> listeArbres){
		ArrayList<Arbre> remarquables = new ArrayList<Arbre>();
		for(Arbre arbre : listeArbres){
			if(arbre.getRemarquable())remarquables.add(arbre);
		}
		for(Membre membre : membres){
			if(Math.random()>0.4){
				float coût = (float)Math.floor(Math.random() * 50 * 100)/100.0f;
				Visite visite = new Visite(membre,remarquables.get((int)(Math.random() * (remarquables.size()))), coût);
				boolean occupée = false;
				for(Visite vis : listeVisites){
					if(vis.getArbre() == visite.getArbre())occupée=true;
				}
				if(!occupée && visite.getCout() < this.getFonds()){
					listeVisites.add(visite);
					payer(membre, coût);
					rapport.append(new Date().toString() + "- Une visite pour " + visite.getArbre().getNom() +" a été programmée.\n");
				}
			}
		}
	}

	/**
	 * Paye un membre d'un certant montant
	 * @param membre membre à payer
	 * @param montant montant à payer
	 */
	public void payer(Membre membre, float montant){
		membre.changeFonds(montant);
		this.changeFonds(-montant);
		Operation operation = new Operation(this,membre, montant);
		operations.add(operation);
		rapport.append(operation.getDate().toString() + "- "  + membre.getNom() + " " + membre.getPrenom() + " a été payé " + montant + ".\n");
	}



}
