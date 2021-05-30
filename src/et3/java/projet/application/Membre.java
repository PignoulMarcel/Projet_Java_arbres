package et3.java.projet.application;

import java.util.Date;

public class Membre extends Personne{
    Association association;
    Date CotisationPayé;
    
    public void setCotisationPayé(Date date) {
    	CotisationPayé = date;
    }
    
    public Date getCotisationPayé() {
    	return CotisationPayé;
    }
    
    public void genererRecette(){

    }

    public void visite(Arbre arbre){

    }

    public Vote vote(){

    }
    
    

}
