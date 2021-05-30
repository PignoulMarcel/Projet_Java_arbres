package et3.java.projet.application;

import java.util.ArrayList;

public class OperationArbre {

    OperationArbre(Arbre arbre, TypeModif type, ArrayList<AmateurDArbres> notifies){
        for(AmateurDArbres amateur : notifies){
            amateur.getNotification(arbre, type);
        }
    }
}
