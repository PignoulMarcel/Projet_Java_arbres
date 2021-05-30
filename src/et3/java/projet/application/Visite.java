package et3.java.projet.application;

public class Visite {
    private Membre membre;
    private Arbre arbre;

    public Visite(Membre membre, Arbre arbre){
        this.membre=membre;
        this.arbre=arbre;
    }

    public Membre getMembre() {
        return membre;
    }

    public Arbre getArbre() {
        return arbre;
    }
}
