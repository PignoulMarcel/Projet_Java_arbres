package et3.java.projet.application;

import java.util.Date;

public class Operation {
    private AmateurDArbres debiteur;
    private AmateurDArbres crediteur;
    private Date date;
    private int montant;

    public Operation(AmateurDArbres debiteur, AmateurDArbres crediteur, Date date, int montant) {
        this.debiteur = debiteur;
        this.crediteur = crediteur;
        this.date = date;
        this.montant = montant;
    }

    public int getMontant() {
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

    public void setMontant(int montant) {
        this.montant = montant;

    }
}
