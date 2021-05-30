package et3.java.projet.application;

import java.util.Date;

public class Visite {
    private Membre membre;
    private Arbre arbre;
    private Date date;
    private float cout;

    public Visite(Membre membre, Arbre arbre, float cout){
        this.membre=membre;
        this.arbre=arbre;
        this.date = new Date();
        this.cout = cout;
    }

    public Membre getMembre() {
        return membre;
    }

    public Arbre getArbre() {
        return arbre;
    }

    public Date getDate() {
        return date;
    }

    public float getCout() {
        return cout;
    }
}
