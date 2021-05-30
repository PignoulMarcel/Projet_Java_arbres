package et3.java.projet.application;

import java.util.ArrayList;
import java.util.Date;

public class OperationArbre {
    private Arbre arbre;
    private TypeModif modif;
    private Date dateModif;

    public OperationArbre(Arbre arbre, TypeModif modif, Date dateModif) {
        this.arbre = arbre;
        this.modif = modif;
        this.dateModif = dateModif;
    }

}
