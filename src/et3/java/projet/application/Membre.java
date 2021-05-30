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

    /**
     * Vote pour ses arbres préférés
     * @param arbresDispo arbres sur lesquels voter
     * @return Arbres votés
     */
    protected Vote vote(ArrayList<Arbre> arbresDispo){
        //protected pour ne passer que les arbresDispo en paramètres(et que seulement l'association puisse connaitre le votre)
        Set<Arbre> arbreVote = new HashSet<Arbre>();
        //Peut être long puisque l'on enlève pas les arbres déjà choisis mais negligeable car faible proba que cela arrive
        while (arbreVote.size()<Math.min(arbresDispo.size(), 5)){
            arbreVote.add(arbresDispo.get((int)(Math.random() * arbresDispo.size())));
        }
        return new Vote((ArrayList<Arbre>) arbreVote);
    }

    public ArrayList<Operation> getCotisations() {
        return cotisations;
    }

    public void ajoutCotisation(Operation cotisation){
        cotisations.add(cotisation);
    }

}
