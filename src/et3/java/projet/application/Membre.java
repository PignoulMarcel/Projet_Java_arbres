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
        //super.super(personne.getId());//TODO
        super(personne.getNom(),personne.getPrenom(),personne.getDateDeNaissance(),personne.getAdresse());
        this.dateArrivee = date;
        cotisations = new ArrayList<Operation>();
        personne = null;

    }
    @SuppressWarnings("deprecation")
	public void genererRecette(){
    	int indice = 0;
    	for(int i = 0; i<cotisations.size(); i++) {
    		if((System.currentTimeMillis() -cotisations.get(i).getDate().getTime()) < new Date(1,0,0).getTime()) {
    			indice +=1;
    			System.out.println("Cotisation n°" + indice + ", le "+ cotisations.get(i).getDate().toString() +", de " + cotisations.get(i).getDebiteur() + " à " + cotisations.get(i).getCrediteur() + " pour un montant de " + cotisations.get(i).getMontant() + "\n");
    
    		}
    	}
    }

    public Visite visite(Arbre arbre){
        return new Visite(this, arbre);
    }

    public Vote vote(ArrayList<Arbre> arbresDispo){
        Set<Arbre> arbreVote = new HashSet<Arbre>();
        //Peut Ãªtre long puisque l'on enlÃ¨ve pas les arbres dÃ©jÃ  choisis mais negligeable car faible proba que cela arrive
        while (arbreVote.size()<Math.min(arbresDispo.size(), 5)){
            arbreVote.add(arbresDispo.get((int)(Math.random() * arbresDispo.size())));
        }
        return new Vote(this, (ArrayList<Arbre>) arbreVote);
    }
    
    public Operation getDerniereOperation() {
    	
    	Operation derniere = cotisations.get(0);		//On suppose qu'il existe au minimum 1 opération
    	for(int i =1; i<cotisations.size(); i++) {
    		if(cotisations.get(i).getDate().before(derniere.getDate())) {
    			derniere = cotisations.get(i);
    		}
    	}
    	return derniere;
    }
    
    

}
