package et3.java.projet.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vote {
    private Membre membre;
    private ArrayList<Arbre> listeArbre;

    public Vote(Membre membre, ArrayList<Arbre> arbreVote){
        Set<Arbre> mySet = new HashSet<Arbre>(arbreVote);
        List<Arbre> arbreVoteUniques = new ArrayList<Arbre>(mySet);
        for(int i=0;i<Math.min(arbreVoteUniques.size(),5);i+=1){
            listeArbre.set(i,arbreVoteUniques.get(arbreVoteUniques.size()-1-i));
        }
        this.membre = membre;
    }

    public ArrayList<Arbre> getListeArbre() {
        return listeArbre;
    }

    public Membre getMembre() {
        return membre;
    }
}
