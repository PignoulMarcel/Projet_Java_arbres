package et3.java.projet.application;

import java.util.Date;

public class Visite {
    private Membre membre;
    private Arbre arbre;
    private Date date;

    public Visite(Membre membre, Arbre arbre, Date date){
        this.membre=membre;
        this.arbre=arbre;
        this.date = date;
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
}
