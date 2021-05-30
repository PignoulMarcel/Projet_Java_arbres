package et3.java.projet.application;

import java.util.ArrayList;
import java.util.Date;


public class AmateurDArbres {
    private final int id;
    private static Mairie mairie;
    private ArrayList<OperationArbre> operationArbres;

    private float fonds = 0;
    private static int incId = 0;

    public AmateurDArbres(){
        this.id = incId;
        incId += 1;
    }

    public float getFonds(){
    	return fonds;
    }
    public void setFonds(float fonds) {
    	this.fonds = fonds;
    }

    protected AmateurDArbres(int id){
        this.id = id;
    }

    public int getId() {
    	return id;
    }

    public void getNotification(OperationArbre operation){
        operationArbres.add(operation);
    }
    
}
