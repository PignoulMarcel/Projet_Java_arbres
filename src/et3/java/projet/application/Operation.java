package et3.java.projet.application;

import java.util.Date;

public class Operation {
    private AmateurDArbres debiteur;
    private AmateurDArbres crediteur;
    private Date date;
    private float montant;

    public Operation(AmateurDArbres debiteur, AmateurDArbres crediteur, float montant) {
        this.debiteur = debiteur;
        this.crediteur = crediteur;
        this.date = new Date();
        this.montant = montant;
    }

    public float getMontant() {
        return montant;
    }
    public AmateurDArbres getDebiteur() {
    	return debiteur;
    }
    public AmateurDArbres getCrediteur() {
    	return crediteur;
    }
    public Date getDate() {
    	return date;
    }

}
