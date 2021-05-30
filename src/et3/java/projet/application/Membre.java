package et3.java.projet.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Membre extends Personne{
    private Association association;
    private Date dateArrivee;
    private ArrayList<Operation> cotisations;


    public Membre(Personne personne, Date date){
        super(personne.getNom(),personne.getPrenom(),personne.getDateDeNaissance(),personne.getAdresse(), personne.getId());
        this.dateArrivee = date;
        cotisations = new ArrayList<Operation>();
        personne = null;

    }


	public void genererRecette(){
    	int indice = 0;
    	for(int i = 0; i<cotisations.size(); i++) {
    		if((System.currentTimeMillis() -cotisations.get(i).getDate().getTime()) < new Date(1,0,0).getTime()) {
    			indice +=1;
    			System.out.println("Cotisation n°" + indice + ", le "+ cotisations.get(i).getDate().toString() +", de " + cotisations.get(i).getDebiteur() + " � " + cotisations.get(i).getCrediteur() + " pour un montant de " + cotisations.get(i).getMontant() + "\n");
    
    		}
    	}
    }

    public Visite visite(Arbre arbre){
        return new Visite(this, arbre, new Date());
    }

    protected Vote vote(ArrayList<Arbre> arbresDispo){
        //protected pour ne passer que les arbresDispo en paramètres(et que seulement l'association puisse connaitre le votre)
        Set<Arbre> arbreVote = new HashSet<Arbre>();
        //Peut être long puisque l'on enlève pas les arbres déjà choisis mais negligeable car faible proba que cela arrive
        while (arbreVote.size()<Math.min(arbresDispo.size(), 5)){
            arbreVote.add(arbresDispo.get((int)(Math.random() * arbresDispo.size())));
        }
        return new Vote((ArrayList<Arbre>) arbreVote);
    }
    
    public Operation getDerniereOperation() {
    	
    	Operation derniere = cotisations.get(0);		//On suppose qu'il existe au minimum 1 op�ration
    	for(int i =1; i<cotisations.size(); i++) {
    		if(cotisations.get(i).getDate().before(derniere.getDate())) {
    			derniere = cotisations.get(i);
    		}
    	}
    	return derniere;
    }

    public ArrayList<Operation> getCotisations() {
        return cotisations;
    }

}
